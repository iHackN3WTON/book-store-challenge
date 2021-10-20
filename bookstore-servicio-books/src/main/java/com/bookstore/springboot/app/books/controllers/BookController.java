package com.bookstore.springboot.app.books.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.springboot.app.books.models.entity.Book;
import com.bookstore.springboot.app.books.models.entity.BookList;
import com.bookstore.springboot.app.books.models.service.IBookService;

@RestController
public class BookController {
	
	@Autowired
	private IBookService bookService;
	
	@GetMapping("/")
	public BookList listar(@RequestParam(value="unavailable", required = false, defaultValue = "false") Boolean unavailable, 
			@RequestParam(value="sort", required = false, defaultValue="bookId,asc") String sort,
			@RequestParam(value="page", required = false, defaultValue="1") Integer page,
			@RequestParam(value="size", required = false, defaultValue="12") Integer size){
		String campo = sort.split(",")[0];
		String orden = sort.split(",").length > 1 ? sort.split(",")[1] : "asc";

		return bookService.findAll(unavailable, page, size, campo, orden);
	}
	
	@GetMapping("/{bookId}")
	public Book ver(@PathVariable Long bookId) {
		return bookService.findById(bookId);
	}
	
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Book crear(@RequestBody Book book) {
		book.setAvailable(true);
		return bookService.save(book);
	}

	@PutMapping("/{bookId}")
	@ResponseStatus(HttpStatus.CREATED)
	public Book editar(@RequestBody Book book, @PathVariable Long bookId) {
		Book bookDb = bookService.findById(bookId);
		if (bookDb.getBookId() != 0) { 
			bookDb.setTitle(book.getTitle());
			bookDb.setDescription(book.getDescription());
			bookDb.setStock(book.getStock());
			bookDb.setSalePrice(book.getSalePrice());
			bookDb.setAvailable(book.getAvailable());
			return bookService.save(bookDb);
		}else {
			return bookDb;
		}
		
	}
	
	@PatchMapping("/{bookId}")
	@ResponseStatus(HttpStatus.CREATED)
	public Book actualizar(@RequestBody Book book, @PathVariable Long bookId) {
		Book bookDb = bookService.findById(bookId);
		if (bookDb.getBookId() != 0) {
			bookDb.setTitle(book.getTitle());
			bookDb.setDescription(book.getDescription());
			bookDb.setStock(book.getStock());
			bookDb.setSalePrice(book.getSalePrice());
			bookDb.setAvailable(book.getAvailable());
			return bookService.save(bookDb);
		}else {
			return bookDb;
		}
		
	}
	
	@DeleteMapping("/{bookId}")
	@ResponseStatus(HttpStatus.OK)
	public void eliminar(@PathVariable Long bookId) {
		Book bookDb = bookService.findById(bookId);
		if (bookDb.getBookId() != 0) bookService.deleteById(bookId);
	}
	
	
}
