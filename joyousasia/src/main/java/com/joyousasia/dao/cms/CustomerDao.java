package com.joyousasia.dao.cms;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.joyousasia.dao.BaseJPARepository;
import com.joyousasia.model.CustomerDTO;


@Repository
public interface CustomerDao extends BaseJPARepository<CustomerDTO, Long>, JpaSpecificationExecutor<CustomerDTO> {
	
	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN 'true' ELSE 'false' END FROM CustomerDTO c WHERE c.identificationNum = ?1 AND c.event = ?2")
    boolean existsByIdentificationNumAndEvent(String identificationNum, String event);

	List<CustomerDTO> findByIdentificationNumAndEvent(String identificationNum,
			String event);
	
	void deleteByCreatedDateLessThan(Date previousYear) throws Exception;

}
