package com.bookstore.springboot.app.transactions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class BookstoreServicioTransactionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreServicioTransactionsApplication.class, args);
	}

}
