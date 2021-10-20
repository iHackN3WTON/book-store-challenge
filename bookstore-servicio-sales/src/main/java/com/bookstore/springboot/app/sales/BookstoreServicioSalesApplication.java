package com.bookstore.springboot.app.sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class BookstoreServicioSalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreServicioSalesApplication.class, args);
	}

}
