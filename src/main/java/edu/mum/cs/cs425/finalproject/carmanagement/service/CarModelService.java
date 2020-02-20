package edu.mum.cs.cs425.finalproject.carmanagement.service;

import java.util.List;

import edu.mum.cs.cs425.finalproject.carmanagement.model.CarModel;

public interface CarModelService {

	public abstract List<CarModel> getAllCarModels();
	public abstract CarModel findCarModelById(Integer id);
}
