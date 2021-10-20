package com.bookstore.springboot.app.transactions.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bookstore.springboot.app.transactions.models.entity.Transaction;
import com.bookstore.springboot.app.transactions.models.entity.service.ITransactionService;

@RestController
public class TransactionController {
	
	@Autowired
	private ITransactionService transactionService;
	
	@GetMapping("/books/{bookId}")
	public Transaction listar(@PathVariable Long bookId,
			@RequestParam String from,
			@RequestParam String to) {
		LocalDate dfrom = LocalDate.parse(from);
		LocalDate dto = LocalDate.parse(to);
		Transaction transaction = transactionService.getTransaction(bookId, dfrom, dto);
		if (transaction.getSales().size() == 0) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encuentran registros");
		}
		return transaction;
	}

}
