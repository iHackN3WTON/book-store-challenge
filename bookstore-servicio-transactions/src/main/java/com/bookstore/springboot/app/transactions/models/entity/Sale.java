package com.bookstore.springboot.app.transactions.models.entity;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Sale implements Serializable {

	private Long id;
	private Long bookId;
	private String customerEmail;
	private Double price;
	private LocalDate saleDate;

	private static final long serialVersionUID = 6590182303238568378L;

}
