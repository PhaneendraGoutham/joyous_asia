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
import com.joyousasia.dao.cms.SchoolDao;
import com.joyousasia.model.SchoolDTO;
import com.joyousasia.model.filter.DataTableParameterObj;

@Service
public class SchoolServiceImpl extends BaseService{
	
	private static final Logger log = Logger.getLogger(SchoolServiceImpl.class);
	
	private static final Integer SCHOOL_INACTIVE = 0;
	private static final Integer SCHOOL_ACTIVE = 1;
	private static final String COLUMN_0 = "schoolId";
	private static final String COLUMN_1 = "schoolName";
	private static final String COLUMN_2 = "status";
	
	@Autowired  
	private SchoolDao schoolDao;
	
	@Transactional
	public List<SchoolDTO> getAllSchools() {
		log.debug("| SchoolServiceImpl | getAllSchools | entry");
		log.debug("| SchoolServiceImpl | getAllSchools | exit");
		return schoolDao.findAll();
	}
	
	@Transactional
	public List<SchoolDTO> getAllActiveSchools() {
		log.debug("| SchoolServiceImpl | getAllActiveSchools | entry");
		log.debug("| SchoolServiceImpl | getAllActiveSchools | exit");
		return schoolDao.findByStatus(SCHOOL_ACTIVE);
	}

	@Transactional
	public AjaxListResponse getSchoolAjaxListResponse(
			DataTableParameterObj dataTableParameters) {
		
		log.debug("| SchoolServiceImpl | getSchoolAjaxListResponse | entry");
		
		List<SchoolDTO> searchResults = schoolDao.findAll();
		Pageable pageRequest =  setUpPageRequest(dataTableParameters);
		Page<SchoolDTO> onePageSearchResults = schoolDao.findAll(pageRequest);
		
		Long totalRecords = (long)searchResults.size();
		
		AjaxListResponse resp = setUpAjaxListResponse(dataTableParameters.getsEcho(), totalRecords, onePageSearchResults);
		
		log.debug("| SchoolServiceImpl | getSchoolAjaxListResponse | exit");
		return resp;
		
	}
	
	private Pageable setUpPageRequest(DataTableParameterObj dataTableParameters) {
		
		log.debug("| SchoolServiceImpl | setUpPageRequest | entry");
		
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
		
		log.debug("| SchoolServiceImpl | setUpPageRequest | exit");
		return new PageRequest(pageIndex, displayLength, direction, columnName);
	}
	
	private AjaxListResponse setUpAjaxListResponse(
			String sEcho, Long totalRecords,
			Page<SchoolDTO> onePageSearchResults) {
		
		log.debug("| SchoolServiceImpl  | AjaxListResponse | entry");
		
		String statusActive = "<input class=\"statusToggle statusActive\" type=\"checkbox\" checked data-toggle=\"toggle\" data-on=\"Active\" data-off=\"Suspend\" data-onstyle=\"success\" data-offstyle=\"danger\" data-style=\"quick\">";
		String statusInactive = "<input class=\"statusToggle statusSuspend\" type=\"checkbox\" checked=\"\" data-toggle=\"toggle\" data-on=\"Active\" data-off=\"Suspend\" data-onstyle=\"success\" data-offstyle=\"danger\" data-style=\"quick\">";
		
		AjaxListResponse resp = new AjaxListResponse();
		resp.setsEcho(sEcho);
		resp.setiTotalRecords(totalRecords);
		resp.setiTotalDisplayRecords(totalRecords);
		List<Map<String, Object>> aaList = new LinkedList<Map<String, Object>>();
		for (SchoolDTO s : onePageSearchResults) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("0", s.getSchoolId());
			m.put("1", s.getSchoolName());
			m.put("2", s.getStatus()==1 ? statusActive : statusInactive);
			m.put("DT_RowId", s.getSchoolId());
			aaList.add(m);
		}
	
		resp.setAaData(aaList);
		log.debug("| SchoolServiceImpl  | AjaxListResponse | exit");
		return resp;
	}
	
	@Transactional
	public void saveSchoolBySchoolIdAndStatus(Integer schoolId, Integer status) throws Exception{
		log.debug("| SchoolServiceImpl  | saveSchoolBySchoolIdAndStatus | entry");
		SchoolDTO school = schoolDao.findOne(schoolId);
		school.setStatus(status);
		schoolDao.save(school);
		log.debug("| SchoolServiceImpl  | saveSchoolBySchoolIdAndStatus | exit");
	}
	
	@Transactional
	public void inactiveAllSchools() throws Exception{
		log.debug("| SchoolServiceImpl  | inactiveAllSchools | entry");
		List<SchoolDTO> schoolList = schoolDao.findAll();
		for(SchoolDTO school : schoolList) {
			if(school.getStatus()!=SCHOOL_INACTIVE) {
				school.setStatus(SCHOOL_INACTIVE);
				schoolDao.save(school);
			}
		}
		log.debug("| SchoolServiceImpl  | inactiveAllSchools | exit");
	}

	
}
