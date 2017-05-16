package com.joyousasia.dao.cms;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.joyousasia.dao.BaseJPARepository;
import com.joyousasia.model.EventDTO;


@Repository
public interface EventDao extends BaseJPARepository<EventDTO, Integer> {
	
	@Query("SELECT CASE WHEN COUNT(e) > 0 THEN 'true' ELSE 'false' END FROM EventDTO e WHERE e.eventName = ?1")
    boolean existsByEventName(String eventName);

	List<EventDTO> findByEventName(String eventName);

	List<EventDTO> findByStatus(Integer status);

}
