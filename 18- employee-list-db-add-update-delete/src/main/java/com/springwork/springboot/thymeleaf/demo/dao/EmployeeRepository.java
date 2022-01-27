package com.springwork.springboot.thymeleaf.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.springwork.springboot.thymeleaf.demo.entity.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	// add a method to sort by last name
	public List<Employee> findAllByOrderByLastnameAsc();

}
