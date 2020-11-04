import logging
from datetime import datetime
import uuid
import json
import boto3
import os

logger = logging.getLogger()
logger.setLevel(logging.INFO)


def lambda_handler(event, context):
    today = datetime.today()

    body = json.loads(event['body'])
    text = body['text']

    dynamodb = boto3.resource('dynamodb')
    table = dynamodb.Table(os.environ['DYNAMODB_TABLE'])
    item = {
        'id': str(uuid.uuid4()),
        'text': text,
        'date': today
    }
    table.put_item(Item = item)

    return {
        'statusCode': 200,
        'body': json.dumps(item)
    }
