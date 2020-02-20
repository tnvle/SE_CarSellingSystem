package edu.mum.cs.cs425.finalproject.carmanagement.service.impl;


import edu.mum.cs.cs425.finalproject.carmanagement.model.Dealer;
import edu.mum.cs.cs425.finalproject.carmanagement.model.Role;
import edu.mum.cs.cs425.finalproject.carmanagement.model.User;
import edu.mum.cs.cs425.finalproject.carmanagement.repository.IDealerRepository;
import edu.mum.cs.cs425.finalproject.carmanagement.repository.RoleRepository;
import edu.mum.cs.cs425.finalproject.carmanagement.repository.UserRepository;
import edu.mum.cs.cs425.finalproject.carmanagement.service.IDealerService;
import org.apache.el.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.RequestDispatcher;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class DealerServiceImpl implements IDealerService {

    @Autowired
    private IDealerRepository dealerRepository;

    //modify start
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    //modify end


    @Override
    public List<Dealer> getAllDealers() {
        return dealerRepository.findAll(Sort.by("dealerNumber"));
    }

    @Override
    public Page<Dealer> getAllDealers(int pageNo) {
        return dealerRepository.findAll(PageRequest.of(pageNo, 3, Sort.by("dealerNumber")));
    }

    public String encodeDealerPassword(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16); // Strength set as 16
        String encodedPassword = encoder.encode(password);
        return encodedPassword;
    }
    @Override
    public Dealer registerNewDealer(Dealer dealer) {
        //modify start
        Role role = roleRepository.findByName("ROLE_DEALER");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        String dealerPassword = encodeDealerPassword(dealer.getPassword());
        User user = new User(dealer.getName(), dealer.getName(), dealer.getName(), dealer.getEmail(), dealer.getEmail(), dealerPassword, roles);
        userRepository.save(user);

        dealer.setUser(user);
        //modify end
        return dealerRepository.save(dealer);
    }

    @Override
    public Dealer getDealerById(Long dealerId) {
        return dealerRepository.findById(dealerId).orElse(null);
    }
    
    @Override
    public Dealer getDealerByEmail(String email) {
        return dealerRepository.findDealerByEmail(email).orElse(null);
    }

    @Override
    public Dealer saveDealer(Dealer dealer) {
        return dealerRepository.save(dealer);
    }


    @Override
    public void deleteDealerById(Long dealerId) {

        dealerRepository.deleteById(dealerId);
    }

    @Override
    public Optional<Dealer> findByDealerNumber(String dealerNumber) {
        return dealerRepository.findDealerByDealerNumber(dealerNumber);
    }

    @Override
    public Page<Dealer> searchDealers(String searchString, int pageNo) {

            return dealerRepository.findAllByDealerNumberContainingOrNameContainingOrAddressContainingOrWebsiteContainingOrPhoneNumberContainingOrEmailContaining
                    (searchString, searchString, searchString, searchString, searchString, searchString, PageRequest.of(pageNo, 3, Sort.by("dealerNumber")));

    }
}
