package edu.mum.cs.cs425.finalproject.carmanagement.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import edu.mum.cs.cs425.finalproject.carmanagement.model.Customer;
import edu.mum.cs.cs425.finalproject.carmanagement.model.Dealer;
import edu.mum.cs.cs425.finalproject.carmanagement.model.User;
import edu.mum.cs.cs425.finalproject.carmanagement.repository.UserRepository;
import edu.mum.cs.cs425.finalproject.carmanagement.service.CustomerService;
import edu.mum.cs.cs425.finalproject.carmanagement.service.IDealerService;
import edu.mum.cs.cs425.finalproject.carmanagement.service.SecurityService;

@Service
public class SecurityServiceImpl implements SecurityService{
	
	public static String ROLE_ADMIN = "ROLE_ADMIN";
	public static String ROLE_DEALER = "ROLE_DEALER";
	public static String ROLE_USER = "ROLE_USER";

	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private IDealerService dealerService;
	
	@Autowired
	private CustomerService customerService;
	
	@Override
	public String getCurrentUserName() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		} else {
		  username = principal.toString();
		}
		return username;
	}
	
	@Override
	public boolean isAdmin() {
		String userName = getCurrentUserName();
		Optional<User> user = userRepository.findByUsername(userName);
		
		boolean isAdmin = false;
		if(user.isPresent()) {
			String[] userRoles = user.get().getRoles().stream().map((role) -> role.getName()).toArray(String[]::new);
			for(String role: userRoles) {
				if(role.compareTo(ROLE_ADMIN) == 0)
					return true;				
			}			
		}
		
		return isAdmin;
	}

	@Override
	public boolean isDealer() {
		String userName = getCurrentUserName();
		Optional<User> user = userRepository.findByUsername(userName);
		
		boolean isDealer = false;
		if(user.isPresent()) {
			String[] userRoles = user.get().getRoles().stream().map((role) -> role.getName()).toArray(String[]::new);
			for(String role: userRoles) {
				if(role.compareTo(ROLE_ADMIN) == 0)
					return false;
				if(role.compareTo(ROLE_DEALER) == 0)
					isDealer = true;
			}			
		}
		
		return isDealer;
	}

	@Override
	public boolean isCustomer() {
		String userName = getCurrentUserName();
		Optional<User> user = userRepository.findByUsername(userName);
		
		boolean isCustomer = false;
		if(user.isPresent()) {
			String[] userRoles = user.get().getRoles().stream().map((role) -> role.getName()).toArray(String[]::new);
			for(String role: userRoles) {
				if(role.compareTo(ROLE_ADMIN) == 0)
					return false;
				if(role.compareTo(ROLE_DEALER) == 0)
					return false;
				if(role.compareTo(ROLE_USER) == 0)
					isCustomer = true;
			}			
		}
		
		return isCustomer;
	}

	@Override
	public Dealer getDealerByUserName(String userName) {
		return dealerService.getDealerByEmail(userName);
	}

	@Override
	public Customer getCustomerByUserName(String userName) {
		return customerService.getCustomerByEmail(userName);
	}
	
	

}
