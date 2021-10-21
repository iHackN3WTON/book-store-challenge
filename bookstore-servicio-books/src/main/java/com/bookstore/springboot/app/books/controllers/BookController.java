package com.bookstore.springboot.app.books.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import lombok.SneakyThrows;

@RestController
public class BookController {
	
	private final Logger log = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	private IBookService bookService;
	
	@GetMapping("/")
	public BookList listar(@RequestParam(value="unavailable", required = false, defaultValue = "false") Boolean unavailable, 
			@RequestParam(value="sort", required = false, defaultValue="bookId,asc") String sort,
			@RequestParam(value="page", required = false, defaultValue="1") Integer page,
			@RequestParam(value="size", required = false, defaultValue="12") Integer size){
		String campo = sort.split(",")[0];
		String orden = sort.split(",").length > 1 ? sort.split(",")[1] : "asc";
		log.info("Solicitando listado de libros pagina " + page + " con " + size + " elementos");
		return bookService.findAll(unavailable, page, size, campo, orden);
	}
	
	@SneakyThrows
	@GetMapping("/{bookId}")
	public Book ver(@PathVariable Long bookId) {
		log.info("Solicitando info de libros " + bookId);
		return bookService.findById(bookId);
	}
	
	@SneakyThrows
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Book crear(@RequestBody Book book) {
		book.setAvailable(true);
		Book resultado = bookService.save(book);
		log.info("Salvando info de libro " + resultado.getBookId());
		return resultado;
	}

	@SneakyThrows
	@PutMapping("/{bookId}")
	@ResponseStatus(HttpStatus.CREATED)
	public Book editar(@RequestBody Book book, @PathVariable Long bookId) {
		log.info("Actualizando info de libro " + bookId);
		Book bookDb = bookService.findById(bookId);
		if (bookDb.getBookId() != 0) { 
			bookDb.setTitle(book.getTitle());
			bookDb.setDescription(book.getDescription());
			bookDb.setStock(book.getStock());
			bookDb.setSalePrice(book.getSalePrice());
			bookDb.setAvailable(book.getAvailable());
			log.info("Salvando info de libro " + book.getBookId());
			return bookService.save(bookDb);
		}else {
			log.info("Info no salvada libro " + book.getBookId() + " no existe");
			return bookDb;
		}
		
	}
	
	@SneakyThrows
	@PatchMapping("/{bookId}")
	@ResponseStatus(HttpStatus.CREATED)
	public Book actualizar(@RequestBody Book book, @PathVariable Long bookId) {
		log.info("Actualizando info de libro " + bookId);
		Book bookDb = bookService.findById(bookId);
		if (bookDb.getBookId() != 0) {
			bookDb.setTitle(book.getTitle());
			bookDb.setDescription(book.getDescription());
			bookDb.setStock(book.getStock());
			bookDb.setSalePrice(book.getSalePrice());
			bookDb.setAvailable(book.getAvailable());
			log.info("Salvando info de libro " + book.getBookId());
			return bookService.save(bookDb);
		}else {
			log.info("Info no salvada libro " + book.getBookId() + " no existe");
			return bookDb;
		}
		
	}
	
	@SneakyThrows
	@DeleteMapping("/{bookId}")
	@ResponseStatus(HttpStatus.OK)
	public void eliminar(@PathVariable Long bookId) {
		log.info("Eliminando info de libro " + bookId);
		Book bookDb = bookService.findById(bookId);
		if (bookDb.getBookId() != 0) bookService.deleteById(bookId);
	}
	
	
}
