package com.joyousasia.dao.cms;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.joyousasia.dao.BaseJPARepository;
import com.joyousasia.model.FacultyDTO;


@Repository
public interface FacultyDao extends BaseJPARepository<FacultyDTO, Integer> {
	
	@Query("SELECT CASE WHEN COUNT(f) > 0 THEN 'true' ELSE 'false' END FROM FacultyDTO f WHERE f.facultyName = ?1")
    boolean existsByFacultyName(String facultyName);

	List<FacultyDTO> findByStatus(Integer status);

}
