package com.bookstore.springboot.app.transactions.models.entity.service;

import java.time.LocalDate;

import com.bookstore.springboot.app.transactions.models.entity.Transaction;

public interface ITransactionService {
	
	public Transaction getTransaction(Long bookId, LocalDate from, LocalDate to);

}
