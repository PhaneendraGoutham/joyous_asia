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
@Table(name="transaction")
public class TransactionDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4066319159856041205L;

	/* Columns Configuration */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "transaction_id", columnDefinition = "bigint", unique = true, nullable = false)
	private Long transactionId;
	
	@Column(name = "customer_id", columnDefinition = "bigint", nullable = false)
	private Long customerId;
	
	@Column(name = "item_id", columnDefinition = "int", nullable = false)
	private Integer itemId;
	
	@Column(name = "item_quantity", columnDefinition = "int default 0", nullable = false)
	private Integer itemQuantity;
	
	@DateTimeFormat(pattern="dd-MM-yyyy HH:mm:ss")
	@Column(name = "last_updated", columnDefinition="datetime")
	private Date lastUpdated;
	
	
	/* Constructor (You can create an constructor for default value setting) */
	public TransactionDTO(){
		
	}
	
	
	/* toString() Function */
	@Override
	public String toString() {
		return "TransactionDTO ["
				+ "transaction_id=" + transactionId + ", "
				+ "customer_id=" + customerId + ", "
				+ "item_id=" + itemId + ", "
				+ "item_quantity=" + itemQuantity + ", "
				+ "last_updated=" + lastUpdated + ", "
				+"]";
	}
	
	
	/* Getter and Setter */
	public Long getTransactionId() {
		return transactionId;
	}


	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}


	public Long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	public Integer getItemId() {
		return itemId;
	}


	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}


	public Integer getItemQuantity() {
		return itemQuantity;
	}


	public void setItemQuantity(Integer itemQuantity) {
		this.itemQuantity = itemQuantity;
	}


	public Date getLastUpdated() {
		return lastUpdated;
	}


	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	
}
