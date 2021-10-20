package com.bookstore.springboot.app.books;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.bookstore.springboot.app.books.models.entity.Book;

@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		config.exposeIdsFor(Book.class);
	}
}
