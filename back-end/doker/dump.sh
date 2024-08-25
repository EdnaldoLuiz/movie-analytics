#!/bin/bash
mysqldump -u ${DATABASE_USERNAME} -p${DATABASE_PASSWORD} -h localhost ${DATABASE_NAME} > ./docker/dump.sql
