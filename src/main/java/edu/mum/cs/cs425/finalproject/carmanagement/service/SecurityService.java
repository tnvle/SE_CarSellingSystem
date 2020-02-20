package edu.mum.cs.cs425.finalproject.carmanagement.service;

import java.util.Optional;

import edu.mum.cs.cs425.finalproject.carmanagement.model.Customer;
import edu.mum.cs.cs425.finalproject.carmanagement.model.Dealer;

public interface SecurityService {
	
	public abstract String getCurrentUserName();
	
	public abstract boolean isAdmin();
	public abstract boolean isDealer();
	public abstract boolean isCustomer();
	
	public abstract Dealer getDealerByUserName(String userName);
	public abstract Customer getCustomerByUserName(String userName);	

}
