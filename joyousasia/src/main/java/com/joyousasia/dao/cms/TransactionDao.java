package com.joyousasia.dao.cms;


import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyousasia.dao.BaseJPARepository;
import com.joyousasia.model.TransactionDTO;


@Repository
public interface TransactionDao extends BaseJPARepository<TransactionDTO, Long> {

	void deleteByCustomerId(Long customerId);

	List<TransactionDTO> findByCustomerId(Long id);
	
	List<TransactionDTO> findByItemId(Integer id);

	void deleteBylastUpdatedLessThan(Date previousYear) throws Exception;
	
}
