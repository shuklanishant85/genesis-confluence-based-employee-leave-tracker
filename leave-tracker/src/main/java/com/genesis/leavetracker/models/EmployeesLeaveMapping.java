package com.genesis.leavetracker.models;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class EmployeesLeaveMapping {

	private Date startDate;
	private Date endDate;
	private Map<String, List<Date>> employeeLeaveMap;

	public EmployeesLeaveMapping() {
		super();
	}

	public EmployeesLeaveMapping(Date startDate, Date endDate, Map<String, List<Date>> employeeLeaveMap) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.employeeLeaveMap = employeeLeaveMap;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Map<String, List<Date>> getEmployeeLeaveMap() {
		return employeeLeaveMap;
	}

	public void setEmployeeLeaveMap(Map<String, List<Date>> employeeLeaveMap) {
		this.employeeLeaveMap = employeeLeaveMap;
	}

}
