package com.bookstore.springboot.app.transactions.models.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Transaction implements Serializable {

	private Long BookId;
	private List<LocalDate> sales;
	private Double totalRevenue;
	private List<String> customers;

	private static final long serialVersionUID = -4773215873691276414L;

}
