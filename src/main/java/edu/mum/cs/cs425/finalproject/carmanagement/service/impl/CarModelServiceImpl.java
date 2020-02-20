package edu.mum.cs.cs425.finalproject.carmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.mum.cs.cs425.finalproject.carmanagement.model.CarModel;
import edu.mum.cs.cs425.finalproject.carmanagement.repository.CarModelRepository;
import edu.mum.cs.cs425.finalproject.carmanagement.service.CarModelService;

@Service
public class CarModelServiceImpl implements CarModelService{
	
	public static String ORDER_BY_COLUMN_NAME = "carModelName";
	
	@Autowired
	private CarModelRepository repository;
	
	@Override
	public List<CarModel> getAllCarModels() {
		return repository.findAll(Sort.by(ORDER_BY_COLUMN_NAME));
	}

	@Override
	public CarModel findCarModelById(Integer id) {
		return repository.findCarModelByCarModelId(id).orElse(null);
	}

}
