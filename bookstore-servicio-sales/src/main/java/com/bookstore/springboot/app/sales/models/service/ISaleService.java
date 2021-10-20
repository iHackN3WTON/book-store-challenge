package com.bookstore.springboot.app.sales.models.service;

import java.util.List;

import com.bookstore.springboot.app.sales.models.entity.Sale;

public interface ISaleService {
	
	public List<Sale> findAll();
	public List<Sale> findAllByBookId(Long bookId);
	public Sale save(Sale sale);
	public void deleteByBookId(Long bookId);

}
