package com.itg.product;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {


    /*@ServiceConnection
    static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:14.5");

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    static {
        postgres.start();
    }
*/
   // @Test
//    void shouldCreateProduct() {
//            String requestBody = """
//                        {
//                           "name": "product2",
//                           "description": "product2 description",
//                           "price": 23
//                         }
//                    """;
//
//            RestAssured.given().contentType("application/json")
//                    .body(requestBody)
//                    .when().post("/api/v1/product")
//                    .then()
//                    .statusCode(201)
//                    .body("name", Matchers.equalTo("product2"))
//                    .body("description", Matchers.equalTo("product2 description"))
//                    .body("price", Matchers.equalTo(23));
//
//
//    }

}
