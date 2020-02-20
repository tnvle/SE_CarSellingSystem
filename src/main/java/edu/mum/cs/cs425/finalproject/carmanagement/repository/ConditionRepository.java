package edu.mum.cs.cs425.finalproject.carmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.cs.cs425.finalproject.carmanagement.model.Condition;

@Repository
public interface ConditionRepository extends JpaRepository<Condition, Integer>{

}
