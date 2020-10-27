package com.renevangool.sqsconsumer.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.*;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;

@Component
public class MessageController {
    private static final String QUEUE = "SQSExampleQueue";
    private static final String S3BUCKET = "sqs-upload-bucket";
    private static String ARNTOPIC = "";

    @Autowired
    private AmazonSQS amazonSQS;

    @Autowired
    private AmazonS3 s3Upload;

    @Autowired
    private AmazonSNS amazonSNS;

    @Scheduled(fixedDelay = 1000, initialDelay = 1000)
    public void receiveMessageFromSqs() throws InterruptedException, IOException {
        final BucketLifecycleConfiguration.Rule expirationRule =
                new BucketLifecycleConfiguration.Rule();
        expirationRule.withExpirationInDays(7).withStatus("Enabled");
        final BucketLifecycleConfiguration lifecycleConfig =
                new BucketLifecycleConfiguration().withRules(expirationRule);

        s3Upload.createBucket(S3BUCKET);
        s3Upload.setBucketLifecycleConfiguration(S3BUCKET, lifecycleConfig);

        final ReceiveMessageRequest receiveMessageRequest =
                new ReceiveMessageRequest(QUEUE);
        List<Message> messages = amazonSQS
                .receiveMessage(receiveMessageRequest).getMessages();

        // Print information about the message.
        for (Message message : messages) {
            System.out.println("\nMessage received.");
            System.out.println("  ID: " + message.getMessageId());
            System.out.println("  Receipt handle: " + message.getReceiptHandle());
            System.out.println("  Message body (first 5 characters): "
                    + message.getBody().substring(0, 5));

            com.renevangool.sqsconsumer.model.Message message1 = new ObjectMapper().readValue(message.getBody(), com.renevangool.sqsconsumer.model.Message.class);
            InputStream inputStream = new ByteArrayInputStream(Base64.getDecoder().decode(message1.getPhoto()));

            Thread.sleep(2000);

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(message1.getContentType());

            PutObjectRequest putObjectRequest = new PutObjectRequest(S3BUCKET, DateTime.now().toString() + message1.getPhotoFileName(), inputStream, objectMetadata);
            s3Upload.putObject(putObjectRequest);

            CreateTopicRequest createTopicRequest = new CreateTopicRequest();
            createTopicRequest.setName("UploadedImage");
            CreateTopicResult response = amazonSNS.createTopic(createTopicRequest);
            ARNTOPIC = response.getTopicArn();

            PublishRequest publishRequest = new PublishRequest();
            publishRequest.setMessage("A new photo has been uploaded to the bucket, check your bucket!");
            publishRequest.setSubject("UploadedImage SNSService Publish Mail");
            publishRequest.setTopicArn(response.getTopicArn());

            PublishResult publishResponse = amazonSNS.publish(publishRequest);
            System.out.println("Message sent: " + publishResponse.getMessageId());

            final String messageReceiptHandle = message.getReceiptHandle();
            amazonSQS.deleteMessage(new DeleteMessageRequest(QUEUE,
                    messageReceiptHandle));
            System.out.println("Deleted the message.");
        }
    }

    private String addSubscriberToTopic() {
        final SubscribeRequest subscribeRequest = new SubscribeRequest();
        subscribeRequest.setTopicArn(ARNTOPIC);
        subscribeRequest.setProtocol("email");
        subscribeRequest.setEndpoint("r.vangool@live.nl");

        SubscribeResult subscribeResult = amazonSNS.subscribe(subscribeRequest);

        return "Subscription ARN request is pending. To confirm the subscription, check your email.";
    }

//    @SqsListener(value = QUEUE, deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
//    public void getMessageFromSqs(@Valid String message, @Header("MessageId") String messageId) throws JsonProcessingException {
//        Message msg = new ObjectMapper().readValue(message, Message.class);
//        LOGGER.info("Received message= {} with messageId= {}", msg, messageId);
//    }
}
