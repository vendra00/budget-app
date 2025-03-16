package com.t1tanic.budgetapp.config;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

public class PostgresTestContainer extends PostgreSQLContainer<PostgresTestContainer> {

    private static final String IMAGE_VERSION = "postgres:16-alpine";
    private static PostgresTestContainer container;

    private PostgresTestContainer() {
        super(DockerImageName.parse(IMAGE_VERSION));
    }

    public static PostgresTestContainer getInstance() {
        if (container == null) {
            container = new PostgresTestContainer();
            container.withDatabaseName("testdb")
                    .withUsername("testuser")
                    .withPassword("testpass");
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("spring.datasource.url", container.getJdbcUrl());
        System.setProperty("spring.datasource.username", container.getUsername());
        System.setProperty("spring.datasource.password", container.getPassword());
    }

    @Override
    public void stop() {
        // No need to stop the container manually, let Testcontainers handle it.
    }
}
