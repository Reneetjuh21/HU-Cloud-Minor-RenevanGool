import json
import sys
import logging
from datetime import datetime
import psycopg2
import uuid

endpoint = 'gatewayassignment.cahtmq6dhg6r.us-east-1.rds.amazonaws.com'
username = 'postgres'
password = 'PietPiraat4%'
database_name = 'postgres'

logger = logging.getLogger()
logger.setLevel(logging.INFO)

try:
    conn_string = "host=%s user=%s password=%s dbname=%s" % \
                  (endpoint, username, password, database_name)
    conn = psycopg2.connect(conn_string)
except:
    logger.error("ERROR: Could not connect to Postgres instance.")
    sys.exit()

logger.info("SUCCESS: Connection to RDS Postgres instance succeeded")


def lambda_handler(event, context):
    today = datetime.today()
    print()

    with conn.cursor() as cur:
        sql = "INSERT INTO notities (`id`,`text`,`date`) VALUES (%s, %s, %s) RETURNING id"
        cur.execute(sql, (uuid.uuid4().hex, event['text'], today))
        id_of_new_row = cur.fetchone()[0]
        conn.commit()

    return {
        'statusCode': 200,
        'body': json.dumps({
            'id': id_of_new_row,
            'text': event['text'],
            'date': today
        })
    }
