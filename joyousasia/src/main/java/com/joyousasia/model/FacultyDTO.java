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
@Table(name="faculty")
public class FacultyDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8989686327854499928L;

	/* Columns Configuration */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "faculty_id", columnDefinition = "int", unique = true, nullable = false)
	private Integer facultyId;
	
	@Column(name = "faculty_name", columnDefinition = "varchar(150)", unique = true, nullable = false)
	private String facultyName;
	
	@Column(name = "status", columnDefinition="int default 0", nullable=false)
	private Integer status;
	
	@DateTimeFormat(pattern="dd-MM-yyyy HH:mm:ss")
	@Column(name = "created_date", columnDefinition="datetime")
	private Date createdDate;
	

	/* Constructor (You can create an constructor for default value setting) */
	public FacultyDTO(){
		
	}
	
	public FacultyDTO(String facultyName, Integer status, Date createdDate) {
		super();
		this.facultyName = facultyName;
		this.status = status;
		this.createdDate = createdDate;
	}


	/* toString() Function */
	@Override
	public String toString() {
		return "FacultyDTO ["
				+ "faculty_id=" + facultyId + ", "
				+ "faculty_name=" + facultyName + ", "
				+ "status=" + status + ","
				+ "created_date" + createdDate
				+"]";
	}
	
	
	/* Getter and Setter */
	public Integer getFacultyId() {
		return facultyId;
	}


	public void setFacultyId(Integer facultyId) {
		this.facultyId = facultyId;
	}


	public String getFacultyName() {
		return facultyName;
	}


	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
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
