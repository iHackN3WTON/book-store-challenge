package com.bookstore.springboot.app.transactions.models.entity.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bookstore.springboot.app.transactions.models.entity.Sale;
import com.bookstore.springboot.app.transactions.models.entity.SaleList;
import com.bookstore.springboot.app.transactions.models.entity.Transaction;

@Service
public class TransactionServiceImpl implements ITransactionService {
	
	@Autowired
	private RestTemplate clienteRest;

	@Override
	public Transaction getTransaction(Long bookId, LocalDate from, LocalDate to) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("bookId", bookId.toString());
		SaleList saleList = clienteRest.getForObject("http://servicio-sales/{bookId}", SaleList.class, pathVariables);
		List<Sale> sales = saleList.getSales().stream().filter(s -> s.getSaleDate().isAfter(from) && s.getSaleDate().isBefore(to)).collect(Collectors.toList());
		Transaction transaction = new Transaction(bookId, new ArrayList<LocalDate>(), 0D, new ArrayList<String>() );
		ArrayList<LocalDate> salesDates = new ArrayList<LocalDate>();
		ArrayList<String> customers = new ArrayList<String>();
		Double totalRevenue = 0D;
		for (int i = 0; i < sales.size(); i++)
		{
			Boolean fechaYaRegistrada = false;
			Boolean customerYaRegistrado = false;
			for (int j = 0; j < salesDates.size(); j++) if (salesDates.get(j).equals(sales.get(i).getSaleDate())) fechaYaRegistrada = true;
			for (int k = 0; k < customers.size(); k++) if (customers.get(k).equals(sales.get(i).getCustomerEmail())) customerYaRegistrado = true;
			
			if (!fechaYaRegistrada) salesDates.add(sales.get(i).getSaleDate());
			if (!customerYaRegistrado) customers.add(sales.get(i).getCustomerEmail());
			
			totalRevenue += sales.get(i).getPrice();
			
		}
		transaction.setSales((List<LocalDate>)salesDates);
		transaction.setTotalRevenue(totalRevenue);
		transaction.setCustomers(customers);
		return transaction;
	}

}
