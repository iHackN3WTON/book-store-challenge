package com.bookstore.springboot.app.sales.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bookstore.springboot.app.sales.models.entity.Sale;

public interface SaleDao extends PagingAndSortingRepository<Sale, Long> {
		

}
