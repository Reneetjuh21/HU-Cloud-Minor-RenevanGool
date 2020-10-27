from __future__ import print_function
import json
import base64
import io
import boto3

s3_client = boto3.client('s3')

def lambda_handler(event, context):
    for record in event['Records']:
       payload=json.loads(record["body"])
       file = io.BytesIO(base64.b64decode(payload.get('photo')))
       s3_client.put_object(Bucket='sqs-upload-bucket', Key=payload.get('photoFileName'), Body=file, ContentType=payload.get('contentType'))