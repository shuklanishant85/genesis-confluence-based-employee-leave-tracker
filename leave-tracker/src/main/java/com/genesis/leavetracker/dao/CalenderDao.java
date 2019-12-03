package com.genesis.leavetracker.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.genesis.leavetracker.models.EmployeesOnLeave;
import com.genesis.leavetracker.models.LeaveData;


public interface CalenderDao {

	LeaveData getEmployeeLeaveData(String employeeName);

	EmployeesOnLeave getEmployeesOnLeave(Date date);

	Map<String, List<Date>> getEmployeeLeaveMap(Date startDate, Date endDate);

	List<Date> getEmployeeLeaveDates(String employeeId, Date startDate, Date endDate);

}
