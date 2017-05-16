package com.joyousasia.service.cms;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyousasia.dao.cms.TransactionDao;
import com.joyousasia.model.TransactionDTO;

@Service
public class TransactionServiceImpl extends BaseService{
	
	private static final Logger log = Logger.getLogger(TransactionServiceImpl.class);
	
	@Autowired  
	private TransactionDao transationDao;
	
	@Transactional
	public void saveTransactions(
			Long customerId,
			String itemQuantityString,
			boolean isUpdate
			) throws Exception{
		
		log.debug("| TransactionServiceImpl | saveTransaction() | entry");
		
		String[] itemQuantityCollectionArray = itemQuantityString.split(",");
		if(isUpdate) {
			
			transationDao.deleteByCustomerId(customerId);
			
		}
		
		for(String itemQuantity : itemQuantityCollectionArray) {
			TransactionDTO transaction = setUpTransactionDTO(customerId,itemQuantity);
			transationDao.save(transaction);
			log.debug("| TransactionServiceImpl | saveTransaction() | transaction: " + transaction.toString());
		}
		
		
		log.debug("| TransactionServiceImpl | saveTransaction() | exit");
		
	}

	private TransactionDTO setUpTransactionDTO(Long customerId,
			String itemQuantity) {
		
		TransactionDTO transaction = new TransactionDTO();
		transaction.setCustomerId(customerId);
		String[] itemQuantityArray = itemQuantity.split(":");
		transaction.setItemId(Integer.valueOf(itemQuantityArray[0]));
		transaction.setItemQuantity(Integer.valueOf(itemQuantityArray[1]));
		transaction.setLastUpdated(new Date());
		return transaction;
		
	}

	
}
