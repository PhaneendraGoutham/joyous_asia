package com.joyousasia.service.cms;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyousasia.common.AjaxListResponse;
import com.joyousasia.dao.cms.FacultyDao;
import com.joyousasia.model.FacultyDTO;
import com.joyousasia.model.filter.DataTableParameterObj;

@Service
public class FacultyServiceImpl extends BaseService{
	
	private static final Logger log = Logger.getLogger(FacultyServiceImpl.class);
	
	private static final Integer FACULTY_INACTIVE = 0;
	private static final Integer FACULTY_ACTIVE = 1;
	private static final String COLUMN_0 = "facultyId";
	private static final String COLUMN_1 = "facultyName";
	private static final String COLUMN_2 = "status";
	
	@Autowired  
	private FacultyDao facultyDao;
	
	@Transactional
	public List<FacultyDTO> getAllFacultys() {
		log.debug("| FacultyServiceImpl | getAllFacultys | entry");
		log.debug("| FacultyServiceImpl | getAllFacultys | exit");
		return facultyDao.findAll();
	}
	
	@Transactional
	public List<FacultyDTO> getAllActiveFacultys() {
		log.debug("| FacultyServiceImpl | getAllActiveFacultys | entry");
		log.debug("| FacultyServiceImpl | getAllActiveFacultys | exit");
		return facultyDao.findByStatus(FACULTY_ACTIVE);
	}

	@Transactional
	public AjaxListResponse getFacultyAjaxListResponse(
			DataTableParameterObj dataTableParameters) {
		
		log.debug("| FacultyServiceImpl | getFacultyAjaxListResponse | entry");
		
		List<FacultyDTO> searchResults = facultyDao.findAll();
		Pageable pageRequest =  setUpPageRequest(dataTableParameters);
		Page<FacultyDTO> onePageSearchResults = facultyDao.findAll(pageRequest);
		
		Long totalRecords = (long)searchResults.size();
		
		AjaxListResponse resp = setUpAjaxListResponse(dataTableParameters.getsEcho(), totalRecords, onePageSearchResults);
		
		log.debug("| FacultyServiceImpl | getFacultyAjaxListResponse | exit");
		return resp;
		
	}
	
	private Pageable setUpPageRequest(DataTableParameterObj dataTableParameters) {
		
		log.debug("| FacultyServiceImpl | setUpPageRequest | entry");
		
		String columnName = COLUMN_0;
		switch(dataTableParameters.getiSortCol_0()) {
			case 0: columnName=COLUMN_0;
					break;
			case 1: columnName=COLUMN_1;
					break;
			case 2: columnName=COLUMN_2;
					break;
			default: columnName=COLUMN_0;
					break;
		}
		
		int displayLength = dataTableParameters.getiDisplayLength();
		int pageIndex = dataTableParameters.getiDisplayStart()/displayLength;
		
		Direction direction = Sort.Direction.ASC;
		
		if(StringUtils.equals(dataTableParameters.getsSortDir_0(), SORT_DIRECTION)) {
			direction = Sort.Direction.ASC;
		}else {
			direction = Sort.Direction.DESC;
		}
		
		log.debug("| FacultyServiceImpl | setUpPageRequest | exit");
		return new PageRequest(pageIndex, displayLength, direction, columnName);
	}
	
	private AjaxListResponse setUpAjaxListResponse(
			String sEcho, Long totalRecords,
			Page<FacultyDTO> onePageSearchResults) {
		
		log.debug("| FacultyServiceImpl  | AjaxListResponse | entry");
		
		String statusActive = "<input class=\"statusToggle statusActive\" type=\"checkbox\" checked data-toggle=\"toggle\" data-on=\"Active\" data-off=\"Suspend\" data-onstyle=\"success\" data-offstyle=\"danger\" data-style=\"quick\">";
		String statusInactive = "<input class=\"statusToggle statusSuspend\" type=\"checkbox\" checked=\"\" data-toggle=\"toggle\" data-on=\"Active\" data-off=\"Suspend\" data-onstyle=\"success\" data-offstyle=\"danger\" data-style=\"quick\">";
		
		AjaxListResponse resp = new AjaxListResponse();
		resp.setsEcho(sEcho);
		resp.setiTotalRecords(totalRecords);
		resp.setiTotalDisplayRecords(totalRecords);
		List<Map<String, Object>> aaList = new LinkedList<Map<String, Object>>();
		for (FacultyDTO f : onePageSearchResults) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("0", f.getFacultyId());
			m.put("1", f.getFacultyName());
			m.put("2", f.getStatus()==1 ? statusActive : statusInactive);
			m.put("DT_RowId", f.getFacultyId());
			aaList.add(m);
		}
	
		resp.setAaData(aaList);
		log.debug("| FacultyServiceImpl  | AjaxListResponse | exit");
		return resp;
	}
	
	@Transactional
	public void saveFacultyByFacultyIdAndStatus(Integer facultyId, Integer status) throws Exception{
		log.debug("| FacultyServiceImpl  | saveFacultyByFacultyIdAndStatus | entry");
		FacultyDTO faculty = facultyDao.findOne(facultyId);
		faculty.setStatus(status);
		facultyDao.save(faculty);
		log.debug("| FacultyServiceImpl  | saveFacultyByFacultyIdAndStatus | exit");
	}
	
	@Transactional
	public void inactiveAllFacultys() throws Exception{
		log.debug("| FacultyServiceImpl  | inactiveAllFacultys | entry");
		List<FacultyDTO> facultyList = facultyDao.findAll();
		for(FacultyDTO faculty : facultyList) {
			if(faculty.getStatus()!=FACULTY_INACTIVE) {
				faculty.setStatus(FACULTY_INACTIVE);
				facultyDao.save(faculty);
			}
		}
		log.debug("| FacultyServiceImpl  | inactiveAllFacultys | exit");
	}

	
}
