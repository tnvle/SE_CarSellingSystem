package edu.mum.cs.cs425.finalproject.carmanagement.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.cs.cs425.finalproject.carmanagement.model.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	Optional<Customer> findCustomerByEmail(String email);
}
