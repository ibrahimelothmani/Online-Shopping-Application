package com.ibrahim.product_service;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	static MongoDBContainer mongoDbContainer = new MongoDBContainer("mongo:latest");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		mongoDbContainer.start();
		System.setProperty("spring.data.mongodb.uri", mongoDbContainer.getReplicaSetUrl());
	}
	@Test
	void shouldCreateProduct() {
		String requestBody= """
                {
                    "id": "67b9f6524a0ac90c8a20e1f3",
                    "name": "product1",
                    "description": "product1 description",
                    "price": 100
                }
                """;

		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/products")
				.then()
				.statusCode(201)
				.body("id", org.hamcrest.Matchers.equalTo("67b9f6524a0ac90c8a20e1f3"))
				.body("name", org.hamcrest.Matchers.equalTo("product1"))
				.body("description", org.hamcrest.Matchers.equalTo("product1 description"))
				.body("price", org.hamcrest.Matchers.equalTo(100));


	}

}
