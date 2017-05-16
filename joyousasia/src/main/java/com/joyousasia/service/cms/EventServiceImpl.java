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
import com.joyousasia.dao.cms.EventDao;
import com.joyousasia.model.EventDTO;
import com.joyousasia.model.filter.DataTableParameterObj;

@Service
public class EventServiceImpl extends BaseService{
	
	private static final Logger log = Logger.getLogger(EventServiceImpl.class);
	
	private static final Integer EVENT_INACTIVE = 0;
	private static final Integer EVENT_ACTIVE = 1;
	private static final String COLUMN_0 = "eventId";
	private static final String COLUMN_1 = "eventName";
	private static final String COLUMN_2 = "status";
	
	@Autowired  
	private EventDao eventDao;
	
	@Transactional
	public List<EventDTO> getAllEvents() {
		log.debug("| EventServiceImpl | getAllEvents | entry");
		log.debug("| EventServiceImpl | getAllEvents | exit");
		return eventDao.findAll();
	}
	
	@Transactional
	public List<EventDTO> getAllActiveEvents() {
		log.debug("| EventServiceImpl | getAllActiveEvents | entry");
		log.debug("| EventServiceImpl | getAllActiveEvents | exit");
		return eventDao.findByStatus(EVENT_ACTIVE);
	}
	
	@Transactional
	public String getLatestReturnDateByEventName(String eventName) throws Exception{
		EventDTO event = eventDao.findByEventName(eventName).get(0);
		return SDF_DD_MM_YYYY.format(event.getGownReturnDate());
	}

	@Transactional
	public AjaxListResponse getEventAjaxListResponse(
			DataTableParameterObj dataTableParameters) {
		
		log.debug("| EventServiceImpl | getEventAjaxListResponse | entry");
		
		List<EventDTO> searchResults = eventDao.findAll();
		Pageable pageRequest =  setUpPageRequest(dataTableParameters);
		Page<EventDTO> onePageSearchResults = eventDao.findAll(pageRequest);
		
		Long totalRecords = (long)searchResults.size();
		
		AjaxListResponse resp = setUpAjaxListResponse(dataTableParameters.getsEcho(), totalRecords, onePageSearchResults);
		
		log.debug("| EventServiceImpl | getEventAjaxListResponse | exit");
		return resp;
		
	}
	
	private Pageable setUpPageRequest(DataTableParameterObj dataTableParameters) {
		
		log.debug("| EventServiceImpl | setUpPageRequest | entry");
		
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
		
		log.debug("| EventServiceImpl | setUpPageRequest | exit");
		return new PageRequest(pageIndex, displayLength, direction, columnName);
	}
	
	private AjaxListResponse setUpAjaxListResponse(
			String sEcho, Long totalRecords,
			Page<EventDTO> onePageSearchResults) {
		
		log.debug("| EventServiceImpl  | AjaxListResponse | entry");
		
		String statusActive = "<input class=\"statusToggle statusActive\" type=\"checkbox\" checked data-toggle=\"toggle\" data-on=\"Active\" data-off=\"Suspend\" data-onstyle=\"success\" data-offstyle=\"danger\" data-style=\"quick\">";
		String statusInactive = "<input class=\"statusToggle statusSuspend\" type=\"checkbox\" checked=\"\" data-toggle=\"toggle\" data-on=\"Active\" data-off=\"Suspend\" data-onstyle=\"success\" data-offstyle=\"danger\" data-style=\"quick\">";
		
		AjaxListResponse resp = new AjaxListResponse();
		resp.setsEcho(sEcho);
		resp.setiTotalRecords(totalRecords);
		resp.setiTotalDisplayRecords(totalRecords);
		List<Map<String, Object>> aaList = new LinkedList<Map<String, Object>>();
		for (EventDTO e : onePageSearchResults) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("0", e.getEventId());
			m.put("1", e.getEventName());
			m.put("2", e.getStatus()==1 ? statusActive : statusInactive);
			m.put("DT_RowId", e.getEventId());
			aaList.add(m);
		}
	
		resp.setAaData(aaList);
		log.debug("| EventServiceImpl  | AjaxListResponse | exit");
		return resp;
	}
	
	@Transactional
	public void saveEventByEventIdAndStatus(Integer eventId, Integer status) throws Exception{
		log.debug("| EventServiceImpl  | saveEventByEventIdAndStatus | entry");
		EventDTO event = eventDao.findOne(eventId);
		event.setStatus(status);
		eventDao.save(event);
		log.debug("| EventServiceImpl  | saveEventByEventIdAndStatus | exit");
	}
	
	@Transactional
	public void inactiveAllEvents() throws Exception{
		log.debug("| EventServiceImpl  | inactiveAllEvents | entry");
		List<EventDTO> eventList = eventDao.findAll();
		for(EventDTO event : eventList) {
			if(event.getStatus()!=EVENT_INACTIVE) {
				event.setStatus(EVENT_INACTIVE);
				eventDao.save(event);
			}
		}
		log.debug("| EventServiceImpl  | inactiveAllEvents | exit");
	}

}
