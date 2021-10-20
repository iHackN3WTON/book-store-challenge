package com.bookstore.springboot.app.likes.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bookstore.springboot.app.likes.models.entity.Like;

public interface LikeDao extends PagingAndSortingRepository<Like, Long> {

}
