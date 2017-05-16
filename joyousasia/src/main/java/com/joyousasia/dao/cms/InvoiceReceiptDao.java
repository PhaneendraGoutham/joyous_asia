package com.joyousasia.dao.cms;


import org.springframework.stereotype.Repository;

import com.joyousasia.dao.BaseJPARepository;
import com.joyousasia.model.InvoiceReceiptDTO;


@Repository
public interface InvoiceReceiptDao extends BaseJPARepository<InvoiceReceiptDTO, Integer>{
	
	
}
