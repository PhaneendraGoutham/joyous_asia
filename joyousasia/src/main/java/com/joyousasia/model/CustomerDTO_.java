package com.joyousasia.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CustomerDTO.class)
public abstract class CustomerDTO_ {
	
	public static volatile SingularAttribute<CustomerDTO, String> invoiceNum;
	public static volatile SingularAttribute<CustomerDTO, String> identificationNum;
	public static volatile SingularAttribute<CustomerDTO, String> name;
	public static volatile SingularAttribute<CustomerDTO, String> contactNum;
	public static volatile SingularAttribute<CustomerDTO, String> email;
	public static volatile SingularAttribute<CustomerDTO, String> event;
	
}
