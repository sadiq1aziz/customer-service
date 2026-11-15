package com.app.customer;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

	@Bean
	@ServiceConnection
	PostgreSQLContainer postgresContainer() {
		var dockerImage = DockerImageName
				// ECR reference for image pull to run test
				.parse(
						"public.ecr.aws" +
								"/docker/library/" +
								"postgres:18")
				.asCompatibleSubstituteFor("postgres");
		return new PostgreSQLContainer(dockerImage)
				.withDatabaseName("customer")
				.withInitScript("db-init.sql");
	}

}
