package com.joyousasia.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseJPARepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
}
