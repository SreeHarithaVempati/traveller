package com.concretepage.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.concretepage.entity.CustomerDetails;

public interface CusRepository extends CrudRepository<CustomerDetails, Long>  {
     CustomerDetails   findByMailId(String mailId);
    List<CustomerDetails> findByMailIdAndPassword(String mailId, String password);

	boolean existsByMailId(String mailId);
	CustomerDetails findByCustomerName(String userName);
    
}
