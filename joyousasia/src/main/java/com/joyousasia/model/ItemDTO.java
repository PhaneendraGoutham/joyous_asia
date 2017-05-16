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
@Table(name="item")
public class ItemDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7497450966126889926L;

	/* Columns Configuration */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "item_id", columnDefinition = "int", unique = true, nullable = false)
	private Integer itemId;
	
	@Column(name = "item_name", columnDefinition = "varchar(100)", unique = true, nullable = false)
	private String itemName;
	
	@Column(name = "item_price", columnDefinition = "double default 0", nullable = false)
	private double itemPrice;
	
	@Column(name = "status", columnDefinition="int default 1", nullable=false)
	private Integer status;
	
	@DateTimeFormat(pattern="dd-MM-yyyy HH:mm:ss")
	@Column(name = "created_date", columnDefinition="datetime")
	private Date createdDate;
	
	@DateTimeFormat(pattern="dd-MM-yyyy HH:mm:ss")
	@Column(name = "last_updated", columnDefinition="datetime")
	private Date lastUpdated;
	
	@Column(name = "remarks", columnDefinition="text")
	private String remarks;
	
	
	/* Constructor (You can create an constructor for default value setting) */
	public ItemDTO(){
		
	}
	
	
	/* toString() Function */
	@Override
	public String toString() {
		return "ItemDTO ["
				+ "item_id=" + itemId + ", "
				+ "item_name=" + itemName + ", "
				+ "item_price=" + itemPrice + ", "
				+ "status=" + status + ", "
				+ "created_date=" + createdDate + ", "
				+ "last_updated=" + lastUpdated + ", "
				+ "remarks=" + remarks
				+"]";
	}
	
	
	/* Getter and Setter */
	public Integer getItemId() {
		return itemId;
	}


	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}


	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	

	public double getItemPrice() {
		return itemPrice;
	}


	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
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


	public Date getLastUpdated() {
		return lastUpdated;
	}


	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}
