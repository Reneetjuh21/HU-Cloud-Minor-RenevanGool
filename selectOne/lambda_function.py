import logging
import json
import boto3
import os
from botocore.exceptions import ClientError

logger = logging.getLogger()
logger.setLevel(logging.INFO)


def lambda_handler(event, context):
    dynamodb = boto3.resource('dynamodb')
    table = dynamodb.Table(os.environ['DYNAMODB_TABLE'])
    notitieId = event['pathParameters']['notitieId']

    try:
        response = table.get_item(Key={'id': notitieId})
    except ClientError as e:
        print(e.response['Error']['Message'])
    else:
        return {
            'statusCode': 200,
            'body': json.dumps(response['Item'])
        }
