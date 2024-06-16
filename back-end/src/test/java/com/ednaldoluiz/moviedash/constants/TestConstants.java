package com.ednaldoluiz.moviedash.constants;

public interface TestConstants {

    interface Postgres {
        String VERSION = "postgres:13.13";
        String DATABASE_NAME = "integration-tests-db";
        String USERNAME = "username";
        String PASSWORD = "password";
        int PORT = 5432;
        String INIT_SCRIPT = "data.sql";
    }

    interface Redis {
        String VERSION = "redis:7.2-alpine";
        String PASSWORD = "redis";
        int PORT = 6379;
    }

}
