package edu.mum.cs.cs425.finalproject.carmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.cs.cs425.finalproject.carmanagement.model.CarModel;

import java.util.Optional;

@Repository
public interface CarModelRepository extends JpaRepository<CarModel, Integer>{
    Optional<CarModel> findCarModelByCarModelName(String name);
    Optional<CarModel> findCarModelByCarModelId(Integer id);
}
