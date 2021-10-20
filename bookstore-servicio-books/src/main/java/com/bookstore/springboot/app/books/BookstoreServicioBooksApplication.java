package com.bookstore.springboot.app.books;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class BookstoreServicioBooksApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreServicioBooksApplication.class, args);
	}

}
