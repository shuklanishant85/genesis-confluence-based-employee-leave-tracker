package com.genesis.leavetracker.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.genesis.leavetracker.models.EmployeesLeaveMapping;
import com.genesis.leavetracker.models.EmployeesOnLeave;
import com.genesis.leavetracker.models.LeaveData;
import com.genesis.leavetracker.services.CalenderService;

@RestController
public class CalenderController {

	@Autowired
	private CalenderService calenderService;

	@GetMapping(value = "leaves/employee/{employeeId}/{startDate}/{endDate}", produces = "application/json")
	public LeaveData getLeaveDetails(@PathVariable String employeeId,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
		return calenderService.getEmployeesLeaveDetails(employeeId, startDate, endDate);
	}

	@GetMapping(value = "leaves/date/{date}", produces = "application/json")
	public EmployeesOnLeave getLeaveDetails(
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
		return calenderService.getEmployeesOnLeave(date);
	}

	@GetMapping(value = "leaves/date/{startDate}/{endDate}", produces = "application/json")
	public EmployeesLeaveMapping getLeaveDetails(
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
		return calenderService.getEmployeesLeaveDates(startDate, endDate);
	}

}
