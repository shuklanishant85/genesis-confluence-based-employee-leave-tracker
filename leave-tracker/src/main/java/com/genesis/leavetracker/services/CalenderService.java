package com.genesis.leavetracker.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genesis.leavetracker.dao.CalenderDao;
import com.genesis.leavetracker.models.EmployeesLeaveMapping;
import com.genesis.leavetracker.models.EmployeesOnLeave;
import com.genesis.leavetracker.models.LeaveData;

@Service
public class CalenderService {

	@Autowired
	CalenderDao calenderDao;

	public EmployeesOnLeave getEmployeesOnLeave(Date date) {
		return calenderDao.getEmployeesOnLeave(date);
	}

	public EmployeesLeaveMapping getEmployeesLeaveDates(Date startDate, Date endDate) {
		EmployeesLeaveMapping employeesLeaveMapping = new EmployeesLeaveMapping();
		employeesLeaveMapping.setStartDate(startDate);
		employeesLeaveMapping.setEndDate(endDate);
		employeesLeaveMapping.setEmployeeLeaveMap(calenderDao.getEmployeeLeaveMap(startDate, endDate));
		return employeesLeaveMapping;
	}

	public LeaveData getEmployeesLeaveDetails(String employeeId, Date startDate, Date endDate) {
		LeaveData leaveData = new LeaveData();
		leaveData.setEmployeeId(employeeId);
		leaveData.setLeaveDates(calenderDao.getEmployeeLeaveDates(employeeId, startDate, endDate));
		return leaveData;
	}

}
