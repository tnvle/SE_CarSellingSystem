package edu.mum.cs.cs425.finalproject.carmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.cs.cs425.finalproject.carmanagement.model.Style;

@Repository
public interface StyleRepository extends JpaRepository<Style, Integer>{

}
