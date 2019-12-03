package com.genesis.leavetracker.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genesis.leavetracker.data.EmployeeDetailsRepository;
import com.genesis.leavetracker.models.Employee;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDetailsRepository employeeDetailsRepository;

	public Employee getEmployeeDetails(String id) {
		return employeeDetailsRepository.getEmployeeDetailsMap().get(id);
	}

	public List<Employee> getAllEmployees() {
		List<Employee> employees = new ArrayList<>();
		employees.addAll(employeeDetailsRepository.getEmployeeDetailsMap().values());
		return employees;
	}
}
