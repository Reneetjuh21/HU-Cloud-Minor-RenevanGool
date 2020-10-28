from __future__ import print_function
import json
import base64
import io
import boto3

s3_client = boto3.client('s3')
s3 = boto3.resource('s3')

def lambda_handler(event, context):
    for record in event['Records']:
        payload=json.loads(record["body"])
       
        if 'message' in payload:
            file = io.BytesIO(base64.b64decode(payload.get('photo')))
            s3_client.put_object(Bucket='sqs-upload-bucket', Key=payload.get('photoFileName'), Body=file, ContentType=payload.get('contentType'))
        else:
            bucketName = payload[1]["s3BucketName"]
            itemName = payload[1]["s3Key"]
            srcObject = json.loads(s3.Object(bucketName, itemName).get()["Body"].read().decode('utf-8'))
            file = io.BytesIO(base64.b64decode(srcObject.get('photo')))
            s3_client.put_object(Bucket='sqs-upload-bucket', Key=srcObject.get('photoFileName'), Body=file, ContentType=srcObject.get('contentType'))