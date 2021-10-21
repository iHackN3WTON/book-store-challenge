package com.bookstore.springboot.app.sales.controllers;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
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

import lombok.SneakyThrows;

@RestController
public class SaleController {
	
	private final Logger log = LoggerFactory.getLogger(SaleController.class);
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private CircuitBreakerFactory cbFactory;
	
	@Autowired
	private ISaleService saleService;
	
	@GetMapping("/")
	public List<Sale> listar() {
		log.info("Solicitando listado de ventas");
		return saleService.findAll();
	}

	@SneakyThrows
	@GetMapping("/{bookId}")
	public SaleList getByBookId(@PathVariable Long bookId) {
		log.info("Solicitando listado de ventas del libro " + bookId);
		SaleList saleList = new SaleList();
		saleList.setSales(saleService.findAllByBookId(bookId));
		log.info("Enviando listado de ventas del libro " + bookId);
		return saleList;
	}
	
	@SneakyThrows
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Sale crear(@RequestBody Sale sale) {
		Sale resultado = cbFactory.create("sales").run(() -> saleService.save(sale), e -> crearFail(sale, e));
		log.info("Guardando info de venta " + resultado.getId());
		return resultado;
	}
	
	public Sale crearFail(Sale sale, Throwable e) {
		log.error(e.getMessage());
		return new Sale(0L, 0L, "Error de conexion", 0D, LocalDate.now());
	}
	
	@SneakyThrows
	@DeleteMapping("/{bookId}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteByBookId(@PathVariable Long bookId) {
		log.info("Eliminando ventas del libro " + bookId);
		saleService.deleteByBookId(bookId);
	}

}
