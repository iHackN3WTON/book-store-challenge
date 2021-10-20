package com.bookstore.springboot.app.transactions.models.entity;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class SaleList implements Serializable {

	private List<Sale> sales;
	
	private static final long serialVersionUID = 8615553423701488838L;

}
