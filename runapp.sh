#!/usr/bin/env bash
echo 'Starting my app'
cd '/home/ubuntu/server'
java -jar cnsd17-backend-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod > /dev/null 2> /dev/null < /dev/null &