package com.genesis.leavetracker.models;

import java.util.Date;
import java.util.List;

public class EmployeesOnLeave {

	private Date date;
	private List<Employee> employees;

	public Date getDate() {
		return date;
	}

	public EmployeesOnLeave() {
	}

	public EmployeesOnLeave(Date date, List<Employee> employees) {
		super();
		this.date = date;
		this.employees = employees;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

}
