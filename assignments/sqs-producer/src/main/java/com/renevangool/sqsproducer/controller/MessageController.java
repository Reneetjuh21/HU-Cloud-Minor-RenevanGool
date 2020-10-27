package com.renevangool.sqsproducer.controller;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.renevangool.sqsproducer.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping(value = "/sqs")
public class MessageController {

    private static final String QUEUE = "SQSExampleQueue";

    @Autowired
    private AmazonSQS amazonSQS;

    @PostMapping(value = "/send")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void sendMessageToSqs(@RequestParam(value = "text") @Valid final String requestMessage, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        String photo = Base64.getEncoder().encodeToString(file.getBytes());
        Message message = new Message(requestMessage, photo, file.getOriginalFilename(), file.getContentType());

        // Create a message queue for this example.
        final CreateQueueRequest createQueueRequest =
                new CreateQueueRequest(QUEUE);
        final String myQueueUrl = amazonSQS
                .createQueue(createQueueRequest).getQueueUrl();
        System.out.println("Queue created.");

        // Send the message.
        final SendMessageRequest myMessageRequest =
                new SendMessageRequest(myQueueUrl, new ObjectMapper().writeValueAsString(message));
        amazonSQS.sendMessage(myMessageRequest);
        System.out.println("Sent the message.");
    }
}
