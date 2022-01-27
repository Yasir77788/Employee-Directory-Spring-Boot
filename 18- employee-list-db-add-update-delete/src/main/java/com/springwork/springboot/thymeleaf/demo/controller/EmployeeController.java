package com.springwork.springboot.thymeleaf.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springwork.springboot.thymeleaf.demo.entity.Employee;
import com.springwork.springboot.thymeleaf.demo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	// wiring employeeService
	private EmployeeService employeeService;
	
	@Autowired // constructor injection 
	public EmployeeController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}
	
	// add mapping for "/list"
	@GetMapping("/list")
	public String listEmployees(Model model) {
		// get the employees from the database
		List<Employee> theEmployees = employeeService.findAll();
		model.addAttribute("employeeList", theEmployees);
		
		return "employees/empTable";
		
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		// create model attritube to bind form data
		
		Employee theEmployee = new Employee();
		
		theModel.addAttribute("employee",  theEmployee);
		
		return "employees/employeeForm";
	}
	
	
	@GetMapping("/showFormForUpdate")
	public String showFormforUpdate(@RequestParam("employeeId") int theId, Model theModel) {
		// get the employee from the service
		Employee theEmployee = employeeService.findById(theId);
		
		// set employee as a model attribute to pre-populate the form
		theModel.addAttribute("employee", theEmployee);
		
		// send over to employee form
		return "employees/employeeForm";
	}
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		
		//save the employee
		employeeService.save(theEmployee);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/employees/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId) {
		
		// delete the employee
		employeeService.deleteById(theId);
		
		// rediret to /emplyees/list
		return "redirect:/employees/list";
		
	}

}
