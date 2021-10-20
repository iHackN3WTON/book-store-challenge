package com.bookstore.springboot.app.books.models.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class BookList {
	
	private List<Book> content;
	private int size;
	private int numberOfElements;
	private int totalElements;
	private int totalPages;
	private int number;

}
