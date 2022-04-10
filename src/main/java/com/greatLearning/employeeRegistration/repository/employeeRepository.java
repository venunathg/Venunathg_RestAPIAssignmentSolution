package com.greatLearning.employeeRegistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatLearning.employeeRegistration.entity.employee;

public interface employeeRepository extends JpaRepository<employee, Integer> {




	List<employee> findByFirstnameContainsAndLastnameContainsAllIgnoreCase(String firstname, String lastname);

}
