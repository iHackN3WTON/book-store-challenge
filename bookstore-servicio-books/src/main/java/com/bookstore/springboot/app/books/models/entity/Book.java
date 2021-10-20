package com.bookstore.springboot.app.books.models.entity;

import java.io.Serializable;

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
@Table(name = "books")
@Data @AllArgsConstructor @NoArgsConstructor
public class Book implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookId;
	private String title;
	private String description;
	private Integer stock;
	@Column(name = "saleprice")
	private Double salePrice;
	private Boolean available;
	
	private static final long serialVersionUID = 8331985832734087788L;

}
