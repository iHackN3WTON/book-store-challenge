package com.bookstore.springboot.app.sales.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Book{
	
	private Long bookId;
	private String title;
	private String description;
	private Integer stock;
	private Double salePrice;
	private Boolean available;

}
