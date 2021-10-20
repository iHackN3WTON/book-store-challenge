package com.bookstore.springboot.app.sales.controllers;

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

import com.bookstore.springboot.app.sales.models.entity.Sale;
import com.bookstore.springboot.app.sales.models.entity.SaleList;
import com.bookstore.springboot.app.sales.models.service.ISaleService;

@RestController
public class SaleController {
	
	@Autowired
	private ISaleService saleService;
	
	@GetMapping("/")
	public List<Sale> listar() {
		return saleService.findAll();
	}
	
	@GetMapping("/{bookId}")
	public SaleList getByBookId(@PathVariable Long bookId) {
		SaleList saleList = new SaleList();
		saleList.setSales(saleService.findAllByBookId(bookId));
		return saleList;
	}
	
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Sale crear(@RequestBody Sale sale) {
		return saleService.save(sale);
	}
	
	@DeleteMapping("/{bookId}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteByBookId(@PathVariable Long bookId) {
		saleService.deleteByBookId(bookId);
	}

}
