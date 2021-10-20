package com.bookstore.springboot.app.sales.models.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.bookstore.springboot.app.sales.models.dao.SaleDao;
import com.bookstore.springboot.app.sales.models.entity.Book;
import com.bookstore.springboot.app.sales.models.entity.Sale;

@Service
public class SaleServiceImpl implements ISaleService {
	
	@Autowired
	private SaleDao saleDao;
	
	@Autowired
	private RestTemplate clienteRest;

	@Override
	@Transactional(readOnly = true)
	public List<Sale> findAll() {
		return (List<Sale>) saleDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Sale> findAllByBookId(Long bookId) {
		return ((List<Sale>) saleDao.findAll()).stream().filter(s -> s.getBookId() == bookId).collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public Sale save(Sale sale) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("bookId", sale.getBookId().toString());
		Book book = clienteRest.getForObject("http://servicio-books/{bookId}", Book.class, pathVariables);
		if (!book.getAvailable()) {
			Sale saleFail = new Sale();
			saleFail.setBookId(0L);
			saleFail.setCustomerEmail("El libro no está disponible");
			return saleFail;
		}else {
			book.setStock(book.getStock()-1);
			if (book.getStock() == 0) book.setAvailable(false);
			sale.setPrice(book.getSalePrice());
			sale.setSaleDate(LocalDate.now());
			HttpEntity<Book> body = new HttpEntity<Book>(book);
			clienteRest.exchange("http://servicio-books/{bookId}", HttpMethod.PUT, body, Book.class, pathVariables);
			return saleDao.save(sale);
		}
		
	}

	@Override
	@Transactional
	public void deleteByBookId(Long bookId) {
		List<Sale> sales = ((List<Sale>) saleDao.findAll()).stream().filter(s -> s.getBookId() == bookId).collect(Collectors.toList());
		saleDao.deleteAll(sales);
	}

}
