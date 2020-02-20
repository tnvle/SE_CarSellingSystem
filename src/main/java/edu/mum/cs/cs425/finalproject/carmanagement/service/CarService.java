package edu.mum.cs.cs425.finalproject.carmanagement.service;

import edu.mum.cs.cs425.finalproject.carmanagement.model.CarModel;
import edu.mum.cs.cs425.finalproject.carmanagement.model.Make;
import org.springframework.data.domain.Page;

import edu.mum.cs.cs425.finalproject.carmanagement.model.Car;
import edu.mum.cs.cs425.finalproject.carmanagement.model.Dealer;

import java.util.List;

public interface CarService {

//	public abstract List<Citizen> getAllCitizens();
	public abstract List<Car> searchCars(Make make, CarModel model, String zipcode);
	public abstract Page<Car> getAllCarsPaged(int pageNo);
	public abstract Page<Car> getAllCarsPagedByDealer(int pageNo, Dealer dealer);
	public abstract Car saveCar(Car car);
//	public abstract Double getTotalYearlyIncome();
	
	public abstract Car getCarById(Long carId);	
	
	public abstract void deleteCarById(Long carId);
//	public abstract Optional<Citizen> findByCitizenNumber(Long productNumber);	
	public abstract List<Integer> getYears();
}
