import sys
import logging
import psycopg2
import json

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
    with conn.cursor() as cur:
        sql = "SELECT * FROM notities WHERE id = %(id)s"
        cur.execute(sql, {'id': event['id']})
        notitie = cur.fetchone()

        return {
            'statusCode': 200,
            'body': json.dumps({
                'id': notitie[0],
                'text': notitie[1],
                'date': notitie[2]
            })
        }
