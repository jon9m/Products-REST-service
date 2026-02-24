package com.mmk.rest.restapi;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistrar;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.containers.MySQLContainer;

@TestConfiguration(proxyBeanMethods = false)
public class MyTestContainersConfig {

    @Bean
    @ServiceConnection
    MySQLContainer<?> mysqlContainer() {
        return new MySQLContainer<>(DockerImageName.parse("mysql:8.3.0"));
    }

    @Bean
    DynamicPropertyRegistrar dynamicPropertyRegistrar() {
        var container = new GenericContainer<>(DockerImageName.parse("nginx:latest"))
                .withExposedPorts(80);

        container.start();

        return registry -> {
            registry.add("external.service.port", container::getFirstMappedPort);
        };
    }

}