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
@Table(name="school")
public class SchoolDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5815117110922511460L;

	/* Columns Configuration */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "school_id", columnDefinition = "int", unique = true, nullable = false)
	private Integer schoolId;
	
	@Column(name = "school_name", columnDefinition = "varchar(150)", unique = true, nullable = false)
	private String schoolName;
	
	@Column(name = "status", columnDefinition="int default 0", nullable=false)
	private Integer status;
	
	@DateTimeFormat(pattern="dd-MM-yyyy HH:mm:ss")
	@Column(name = "created_date", columnDefinition="datetime")
	private Date createdDate;
	

	/* Constructor (You can create an constructor for default value setting) */
	public SchoolDTO(){
		
	}
	
	public SchoolDTO(String schoolName, Integer status, Date createdDate) {
		super();
		this.schoolName = schoolName;
		this.status = status;
		this.createdDate = createdDate;
	}


	/* toString() Function */
	@Override
	public String toString() {
		return "SchoolDTO ["
				+ "school_id=" + schoolId + ", "
				+ "school_name=" + schoolName + ", "
				+ "status=" + status + ","
				+ "created_date" + createdDate
				+"]";
	}
	
	
	/* Getter and Setter */
	public Integer getSchoolId() {
		return schoolId;
	}


	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}


	public String getSchoolName() {
		return schoolName;
	}


	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
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
