package com.joyousasia.dao.cms;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.jsoup.helper.StringUtil;
import org.springframework.data.jpa.domain.Specification;

import com.joyousasia.model.CustomerDTO;
import com.joyousasia.model.CustomerDTO_;
import com.joyousasia.model.filter.CustomerFilterObj;

public class CustomerSpecifications {
	
	private CustomerSpecifications() {
		
	}
	
	public static Specification<CustomerDTO> getCustomerListByFilter(final CustomerFilterObj filter) {
        return new Specification<CustomerDTO>() {
        	
			@Override
			public Predicate toPredicate(Root<CustomerDTO> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> predicates = new ArrayList<Predicate>();
				
				if(!StringUtil.isBlank(filter.getIdentificationNum())) {
					predicates.add(cb.like(cb.lower(root.<String>get(CustomerDTO_.identificationNum)), getContainsLikePattern(filter.getIdentificationNum())));
				}
				
				if(!StringUtil.isBlank(filter.getName())) {
					predicates.add(cb.like(cb.lower(root.<String>get(CustomerDTO_.name)), getContainsLikePattern(filter.getName())));
				}
				
				if(!StringUtil.isBlank(filter.getEmail())) {
					predicates.add(cb.like(cb.lower(root.<String>get(CustomerDTO_.email)), getContainsLikePattern(filter.getEmail())));
				}
				
				if(!StringUtil.isBlank(filter.getContactNum())) {
					predicates.add(cb.like(cb.lower(root.<String>get(CustomerDTO_.contactNum)), getContainsLikePattern(filter.getContactNum())));
				}
				
				if(!StringUtil.isBlank(filter.getInvoiceNum())) {
					predicates.add(cb.like(cb.lower(root.<String>get(CustomerDTO_.invoiceNum)), getContainsLikePattern(filter.getInvoiceNum())));
				}
				
				if(!StringUtil.isBlank(filter.getEvent())) {
					predicates.add(cb.like(cb.lower(root.<String>get(CustomerDTO_.event)), getContainsLikePattern(filter.getEvent())));
				}
				
				return cb.and(predicates.toArray(new Predicate[] {}));
			}

        };
    }
	
	private static String getContainsLikePattern(String searchTerm) {
        	
        return "%" + searchTerm.toLowerCase() + "%";
            
    }
	
}
