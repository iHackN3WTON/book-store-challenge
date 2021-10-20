package com.bookstore.springboot.app.sales.models.entity;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class SaleList implements Serializable {
	
	private List<Sale> sales;

	private static final long serialVersionUID = 6772899221589354781L;

}
