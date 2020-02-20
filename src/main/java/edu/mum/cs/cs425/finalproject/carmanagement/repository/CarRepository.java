package edu.mum.cs.cs425.finalproject.carmanagement.repository;

import edu.mum.cs.cs425.finalproject.carmanagement.model.CarModel;
import edu.mum.cs.cs425.finalproject.carmanagement.model.Make;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.cs.cs425.finalproject.carmanagement.model.Car;
import edu.mum.cs.cs425.finalproject.carmanagement.model.Dealer;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{
//    List<Car> findAllByMakeAndCarModel(Make make, CarModel model);
    List<Car> findAllByMake(Make make);
    List<Car> findAllByCarModel(CarModel model);
	
	Page<Car> findAllByDealer(Dealer dealer, Pageable pageable);

    List<Car> findCarsByZipCodeOrMakeOrCarModel(String zipcode, Make make, CarModel model);
    List<Car> findAllByZipCode(String zipCode);
}
