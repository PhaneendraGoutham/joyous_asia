package com.joyousasia.dao;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

public interface BaseCRUDRepository<T, ID extends Serializable> extends CrudRepository<T, ID> {
	
}
