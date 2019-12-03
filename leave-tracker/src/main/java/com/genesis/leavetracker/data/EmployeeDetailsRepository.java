package com.genesis.leavetracker.data;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.genesis.leavetracker.models.Employee;

@Repository
public class EmployeeDetailsRepository {

	private Map<String, Employee> employeeDetailsMap;

	public Map<String, Employee> getEmployeeDetailsMap() {
		return employeeDetailsMap;
	}

	@PostConstruct
	public void initialize() {
		employeeDetailsMap = new HashMap<>();
	}

}
