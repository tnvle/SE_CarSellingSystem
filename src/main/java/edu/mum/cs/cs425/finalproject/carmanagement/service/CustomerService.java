package edu.mum.cs.cs425.finalproject.carmanagement.service;

import edu.mum.cs.cs425.finalproject.carmanagement.model.Car;
import edu.mum.cs.cs425.finalproject.carmanagement.model.Customer;
import edu.mum.cs.cs425.finalproject.carmanagement.model.Dealer;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    public abstract Customer saveCustomer(Customer customer);
    public abstract Customer getCustomerByEmail(String email);
//    public abstract Page<Car> getCustomerSavedCarList(Customer customer);
}   
