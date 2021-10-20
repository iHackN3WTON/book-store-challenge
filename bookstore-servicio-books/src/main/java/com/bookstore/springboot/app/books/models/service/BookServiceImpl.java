package com.bookstore.springboot.app.books.models.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.bookstore.springboot.app.books.models.dao.BookDao;
import com.bookstore.springboot.app.books.models.entity.Book;
import com.bookstore.springboot.app.books.models.entity.BookList;

@Service
public class BookServiceImpl implements IBookService {
	
	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private RestTemplate clienteRest;

	@Override
	@Transactional(readOnly = true)
	public BookList findAll(Boolean unavailable, int page, int size, String campo, String orden) {
		if (!campo.equals("bookId") && !campo.equals("title") && !campo.equals("description") && !campo.equals("salePrice") && !campo.equals("stock") && !campo.endsWith("available")) campo = "bookId";
		BookList bookList = new BookList();
		bookList.setTotalElements(unavailable ? ((List<Book>) bookDao.findAll()).size() : ((List<Book>) bookDao.findAll()).stream().filter(b -> b.getAvailable() == true).collect(Collectors.toList()).size());
		bookList.setSize(size);
		bookList.setNumber(page);
		bookList.setTotalPages((int)Math.ceil((float)bookList.getTotalElements() / (float)bookList.getSize()));
		List<Book> books = (List<Book>) bookDao.findAll(PageRequest.of(page - 1, size, Sort.by(orden.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, campo))).toList();
		if (!unavailable) books = books.stream().filter(b -> b.getAvailable() == true).collect(Collectors.toList());
		bookList.setContent(books);
		bookList.setNumberOfElements(books.size());
		return bookList;
	}

	@Override
	@Transactional(readOnly = true)
	public Book findById(Long bookId) {
		return bookDao.findById(bookId).orElse(new Book(0L, "Libro no encontrado", "", 0, 0D, false));
	}

	@Override
	@Transactional
	public Book save(Book book) {
		return bookDao.save(book);
	}

	@Override
	@Transactional
	public void deleteById(Long bookId) {
		//Delete Book
		bookDao.deleteById(bookId);	
		
		//Delete Book Sales
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("bookId", bookId.toString());
		clienteRest.exchange("http://servicio-sales/{bookId}", HttpMethod.DELETE, null, Book.class, pathVariables);
		
		//Delete Book Likes
		clienteRest.exchange("http://servicio-likes/{bookId}", HttpMethod.DELETE, null, Book.class, pathVariables);
	}

}
