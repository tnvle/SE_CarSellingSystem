package edu.mum.cs.cs425.finalproject.carmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.cs.cs425.finalproject.carmanagement.model.Customer;
import edu.mum.cs.cs425.finalproject.carmanagement.model.Dealer;
import edu.mum.cs.cs425.finalproject.carmanagement.repository.CustomerRepository;
import edu.mum.cs.cs425.finalproject.carmanagement.repository.IDealerRepository;
import edu.mum.cs.cs425.finalproject.carmanagement.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
    private CustomerRepository customerRepository;

	@Override
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	    public Customer getCustomerByEmail(String email) {
	        return customerRepository.findCustomerByEmail(email).orElse(null);
	    }

}
