package com.bookstore.springboot.app.books.models.service;

import com.bookstore.springboot.app.books.models.entity.Book;
import com.bookstore.springboot.app.books.models.entity.BookList;

public interface IBookService {
	
	public BookList findAll(Boolean unavailable, int page, int size, String campo, String orden);
	public Book findById(Long bookId);
	public Book save(Book book);
	public void deleteById(Long bookId);

}
