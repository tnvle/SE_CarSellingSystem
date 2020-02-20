package edu.mum.cs.cs425.finalproject.carmanagement.service.impl;


import edu.mum.cs.cs425.finalproject.carmanagement.model.Dealer;
import edu.mum.cs.cs425.finalproject.carmanagement.model.Role;
import edu.mum.cs.cs425.finalproject.carmanagement.model.User;
import edu.mum.cs.cs425.finalproject.carmanagement.repository.IDealerRepository;
import edu.mum.cs.cs425.finalproject.carmanagement.repository.RoleRepository;
import edu.mum.cs.cs425.finalproject.carmanagement.repository.UserRepository;
import edu.mum.cs.cs425.finalproject.carmanagement.service.IDealerService;
import edu.mum.cs.cs425.finalproject.carmanagement.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public void deleteUserById(Integer userId) {

        userRepository.deleteById(userId);
    }
}
