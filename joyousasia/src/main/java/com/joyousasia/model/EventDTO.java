package com.joyousasia.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="event")
public class EventDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4798126954094323797L;

	/* Columns Configuration */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "event_id", columnDefinition = "int", unique = true, nullable = false)
	private Integer eventId;
	
	@Column(name = "event_name", columnDefinition = "varchar(150)", unique = true, nullable = false)
	private String eventName;
	
	@DateTimeFormat(pattern="dd-MM-yyyy")
	@Column(name = "gown_return_date", columnDefinition="date")
	private Date gownReturnDate;
	
	@Column(name = "status", columnDefinition="int default 0", nullable=false)
	private Integer status;
	
	@DateTimeFormat(pattern="dd-MM-yyyy HH:mm:ss")
	@Column(name = "created_date", columnDefinition="datetime")
	private Date createdDate;
	

	/* Constructor (You can create an constructor for default value setting) */
	public EventDTO(){
		
	}
	
	public EventDTO(String eventName, Integer status, Date createdDate) {
		super();
		this.eventName = eventName;
		this.status = status;
		this.createdDate = createdDate;
	}


	/* toString() Function */
	@Override
	public String toString() {
		return "EventDTO ["
				+ "event_id=" + eventId + ", "
				+ "event_name=" + eventName + ", "
				+ "gown_return_date=" + gownReturnDate + ", "
				+ "status=" + status + ","
				+ "created_date" + createdDate
				+"]";
	}
	
	
	/* Getter and Setter */
	public Integer getEventId() {
		return eventId;
	}


	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}


	public String getEventName() {
		return eventName;
	}


	public void setEventName(String eventName) {
		this.eventName = eventName;
	}


	public Date getGownReturnDate() {
		return gownReturnDate;
	}

	
	public void setGownReturnDate(Date gownReturnDate) {
		this.gownReturnDate = gownReturnDate;
	}

	
	public Integer getStatus() {
		return status;
	}

	
	public void setStatus(Integer status) {
		this.status = status;
	}

	
	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
}
