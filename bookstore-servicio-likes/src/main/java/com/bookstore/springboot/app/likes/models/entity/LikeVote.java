package com.bookstore.springboot.app.likes.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class LikeVote {

	private Long bookId;
	private String customerEmail;
}
