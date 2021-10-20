package com.bookstore.springboot.app.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class BookstoreServicioGatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreServicioGatewayServerApplication.class, args);
	}

}
