package edu.mum.cs.cs425.finalproject.carmanagement.repository;

import edu.mum.cs.cs425.finalproject.carmanagement.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
