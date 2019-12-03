package com.genesis.leavetracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.genesis.leavetracker.models.Employee;
import com.genesis.leavetracker.services.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping(path = "/employee/{employeeId}")
	public Employee getEmployeeDetails(@PathVariable String employeeId) {
		return employeeService.getEmployeeDetails(employeeId);
	}

	@GetMapping(path = "/employees")
	public List<Employee> getAllEmployeeDetails() {
		return employeeService.getAllEmployees();
	}

}
