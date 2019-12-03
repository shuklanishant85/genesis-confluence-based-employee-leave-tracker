package com.genesis.leavetracker.models;

import java.util.Date;
import java.util.List;

public class LeaveData {

	private String employeeId;
	private List<Date> leaveDates;

	public LeaveData() {
	}

	public LeaveData(String employeeId, List<Date> leaveDates) {
		this.employeeId = employeeId;
		this.leaveDates = leaveDates;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public List<Date> getLeaveDates() {
		return leaveDates;
	}

	public void setLeaveDates(List<Date> leaveDates) {
		this.leaveDates = leaveDates;
	}

}
