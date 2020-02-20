package edu.mum.cs.cs425.finalproject.carmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.mum.cs.cs425.finalproject.carmanagement.model.Condition;
import edu.mum.cs.cs425.finalproject.carmanagement.repository.ConditionRepository;
import edu.mum.cs.cs425.finalproject.carmanagement.service.ConditionService;

@Service
public class ConditionServiceImpl implements ConditionService{
	
	public static String ORDER_BY_COLUMN_NAME = "conditionName";
	
	@Autowired
	private ConditionRepository repository;
	
	@Override
	public List<Condition> getAllConditions() {
		return repository.findAll(Sort.by(ORDER_BY_COLUMN_NAME));
	}
	
}
