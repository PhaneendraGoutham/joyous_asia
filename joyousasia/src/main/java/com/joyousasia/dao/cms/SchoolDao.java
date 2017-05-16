package com.joyousasia.dao.cms;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.joyousasia.dao.BaseJPARepository;
import com.joyousasia.model.SchoolDTO;


@Repository
public interface SchoolDao extends BaseJPARepository<SchoolDTO, Integer> {
	
	@Query("SELECT CASE WHEN COUNT(s) > 0 THEN 'true' ELSE 'false' END FROM SchoolDTO s WHERE s.schoolName = ?1")
    boolean existsBySchoolName(String schoolName);

	List<SchoolDTO> findByStatus(Integer status);

}
