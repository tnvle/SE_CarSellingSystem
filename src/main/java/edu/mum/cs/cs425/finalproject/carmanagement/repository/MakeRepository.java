package edu.mum.cs.cs425.finalproject.carmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.cs.cs425.finalproject.carmanagement.model.Make;

import java.util.Optional;

@Repository
public interface MakeRepository extends JpaRepository<Make, Integer>{

    Optional<Make> findMakeByMakeName(String name);
    Optional<Make> findMakeByMakeId(Integer id);
}
