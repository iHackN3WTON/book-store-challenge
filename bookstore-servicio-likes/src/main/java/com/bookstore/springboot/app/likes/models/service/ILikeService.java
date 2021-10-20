package com.bookstore.springboot.app.likes.models.service;

import java.util.List;

import com.bookstore.springboot.app.likes.models.entity.Like;
import com.bookstore.springboot.app.likes.models.entity.LikeVote;

public interface ILikeService {
	
	public List<Like> findAll();
	public Like save(LikeVote likeVote);
	public void deleteByBookId(Long bookId);

}
