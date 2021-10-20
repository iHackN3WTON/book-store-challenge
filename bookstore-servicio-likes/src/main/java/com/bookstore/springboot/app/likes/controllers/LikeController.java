package com.bookstore.springboot.app.likes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.springboot.app.likes.models.entity.Like;
import com.bookstore.springboot.app.likes.models.entity.LikeVote;
import com.bookstore.springboot.app.likes.models.service.ILikeService;

@RestController
public class LikeController {
	@Autowired
	private ILikeService likeService;
	
	@GetMapping("/")
	public List<Like> listar() {
		return likeService.findAll();
	}
	
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Like crear(@RequestBody LikeVote likeVote) {
		return likeService.save(likeVote);
	}
	
	@DeleteMapping("/{bookId}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteByBookId(@PathVariable Long bookId) {
		likeService.deleteByBookId(bookId);
	}
}
