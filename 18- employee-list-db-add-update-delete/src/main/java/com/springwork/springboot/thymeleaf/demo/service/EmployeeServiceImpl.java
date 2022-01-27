package com.springwork.springboot.thymeleaf.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springwork.springboot.thymeleaf.demo.dao.EmployeeRepository;
import com.springwork.springboot.thymeleaf.demo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	
	private EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
		employeeRepository = theEmployeeRepository;
	}
	@Override
	//@Transactional - no need for it as jpa provide it out of the box 
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return employeeRepository.findAllByOrderByLastnameAsc();
	}

	@Override
	//@Transactional 
	public Employee findById(int theId) {
		// TODO Auto-generated method stub
		//return employeeRepository.findById(theId); // need to convert Optional type to Employee type
		                                           // do refactor and extract local variable
		// Optional is used to check for null to see if given value is present
		Optional<Employee> result =  employeeRepository.findById(theId); 
		
		Employee theEmployee = null;
		if(result.isPresent()) {
			theEmployee = result.get();
		}
		else {
			throw new RuntimeException("Did not find the employee id of :" + theId);
		}
		
		return theEmployee;
		
	}

	@Override
	//@Transactional 
	public void save(Employee theEmployee) {
		employeeRepository.save(theEmployee);

	}

	@Override
	//@Transactional 
	public void deleteById(int theId) {
		// TODO Auto-generated method stub
		employeeRepository.deleteById(theId);

	}

}
