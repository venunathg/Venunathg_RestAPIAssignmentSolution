package com.greatLearning.employeeRegistration.service;

import java.util.List;

import com.greatLearning.employeeRegistration.entity.employee;


public interface employeeService {
	public List<employee> findAll();

	public employee findById(int theId);

	public void save(employee theStudent);

	public void deleteById(int theId);

	public List<employee> searchBy(String firstname, String lastname);

}
