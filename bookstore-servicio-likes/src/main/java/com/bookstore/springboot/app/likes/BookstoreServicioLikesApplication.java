package com.bookstore.springboot.app.likes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class BookstoreServicioLikesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreServicioLikesApplication.class, args);
	}

}
