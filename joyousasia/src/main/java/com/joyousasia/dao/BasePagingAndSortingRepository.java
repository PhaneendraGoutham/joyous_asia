package com.joyousasia.dao;

import java.io.Serializable;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface BasePagingAndSortingRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {
	

}
