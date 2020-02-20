package edu.mum.cs.cs425.finalproject.carmanagement.service;

import java.util.List;

import edu.mum.cs.cs425.finalproject.carmanagement.model.Make;

public interface MakeService {
	public abstract Make findMakeById(Integer id);
	public abstract List<Make> getAllMakes();
}
