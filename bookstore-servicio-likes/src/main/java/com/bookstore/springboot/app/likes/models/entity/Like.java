package com.bookstore.springboot.app.likes.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="likes")
@Data @AllArgsConstructor @NoArgsConstructor
public class Like implements Serializable{
	
	@Id
	private Long bookId;
	private int likes;
	@ElementCollection
	@CollectionTable(name = "likes_customers", joinColumns = @JoinColumn(name = "bookId"))
	private List<String> customers;
	
	private static final long serialVersionUID = -661241873845159401L;

}
