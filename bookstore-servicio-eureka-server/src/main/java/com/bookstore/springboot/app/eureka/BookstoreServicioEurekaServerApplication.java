package com.bookstore.springboot.app.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class BookstoreServicioEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreServicioEurekaServerApplication.class, args);
	}

}
