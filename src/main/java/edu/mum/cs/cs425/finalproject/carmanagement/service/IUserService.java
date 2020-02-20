package edu.mum.cs.cs425.finalproject.carmanagement.service;


import edu.mum.cs.cs425.finalproject.carmanagement.model.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    public abstract User getUserById(Integer userId);
    public abstract void deleteUserById(Integer userId);

}   
