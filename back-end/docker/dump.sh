#!/bin/bash
PGPASSWORD=${DATABASE_PASSWORD} pg_dump -U ${DATABASE_USERNAME} -h localhost -d ${DATABASE_NAME} > dump.sql
