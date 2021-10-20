package com.bookstore.springboot.app.sales.models.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="sales")
@Data @AllArgsConstructor @NoArgsConstructor
public class Sale implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long bookId;
	@Column(name="customeremail")
	private String customerEmail;
	private Double price;
	@Column(name="saledate")
	private LocalDate saleDate;
	
	private static final long serialVersionUID = 2283575113803396425L;
}
