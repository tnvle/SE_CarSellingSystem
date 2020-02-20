package edu.mum.cs.cs425.finalproject.carmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.mum.cs.cs425.finalproject.carmanagement.model.Style;
import edu.mum.cs.cs425.finalproject.carmanagement.repository.StyleRepository;
import edu.mum.cs.cs425.finalproject.carmanagement.service.StyleService;

@Service
public class StyleServiceImpl implements StyleService{
	
	public static String ORDER_BY_COLUMN_NAME = "styleName";
	
	@Autowired
	private StyleRepository repository;
	
	@Override
	public List<Style> getAllStyles() {
		return repository.findAll(Sort.by(ORDER_BY_COLUMN_NAME));
	}
	
}
