package com.bookstore.springboot.app.books.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bookstore.springboot.app.books.models.entity.Book;

public interface BookDao extends PagingAndSortingRepository<Book, Long> {

}
