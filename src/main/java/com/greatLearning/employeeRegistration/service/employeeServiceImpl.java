package com.greatLearning.employeeRegistration.service;



import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.greatLearning.employeeRegistration.entity.employee;
import com.greatLearning.employeeRegistration.repository.employeeRepository;

import java.util.ArrayList;
import java.util.List;





@Service
public class employeeServiceImpl implements employeeService {


	@Autowired
	employeeRepository employeeRepository;

	@Override
	public List<employee> findAll() {
		// TODO Auto-generated method stub
		List<employee> employees=employeeRepository.findAll();
		return employees;
	}

	@Override
	public employee findById(int theId) {
		// TODO Auto-generated method stub
		return employeeRepository.findById(theId).get();
	}

	@Override
	public void save(employee theStudent) {
		// TODO Auto-generated method stub
		employeeRepository.save(theStudent);
		
	}

	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub
		employeeRepository.deleteById(theId);
		
	}

	@Override
	public List<employee> searchBy(String firstname, String lastname) {
		// TODO Auto-generated method stub
		List<employee> employees=employeeRepository.findByFirstnameContainsAndLastnameContainsAllIgnoreCase(firstname, lastname);
		
		
  		return employees;
	}





}