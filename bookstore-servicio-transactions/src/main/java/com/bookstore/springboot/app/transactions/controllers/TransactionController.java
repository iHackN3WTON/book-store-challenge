package com.bookstore.springboot.app.transactions.controllers;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bookstore.springboot.app.transactions.models.entity.Transaction;
import com.bookstore.springboot.app.transactions.models.entity.service.ITransactionService;

import lombok.SneakyThrows;

@RestController
public class TransactionController {
	
	private final Logger log = LoggerFactory.getLogger(TransactionController.class);
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private CircuitBreakerFactory cbFactory;
	
	@Autowired
	private ITransactionService transactionService;
	
	@SneakyThrows
	@GetMapping("/books/{bookId}")
	public Transaction listar(@PathVariable Long bookId,
			@RequestParam String from,
			@RequestParam String to) {
		log.info("Solicitando transacciones de libro " + bookId + " desde " + from + " hasta " + to);
		LocalDate dfrom = LocalDate.parse(from);
		LocalDate dto = LocalDate.parse(to);
		Transaction transaction = cbFactory.create("transactions").run(() -> transactionService.getTransaction(bookId, dfrom, dto), e -> listarFail(e));
		if (transaction.getSales().size() == 0) {
			log.error("No existen transacciones de libro " + bookId + " desde " + from + " hasta " + to);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encuentran registros");
		}
		log.info("Enviando transacciones de libro " + bookId + " desde " + from + " hasta " + to);
		return transaction;		
	}
	
	public Transaction listarFail(Throwable e) {
		log.error(e.getMessage());
		return new Transaction(0L, List.of(LocalDate.now()) , 0D, List.of("Error de conexion"));
	}

}
