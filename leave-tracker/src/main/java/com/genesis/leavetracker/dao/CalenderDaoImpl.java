package com.genesis.leavetracker.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.genesis.leavetracker.data.EmployeeDetailsRepository;
import com.genesis.leavetracker.data.LeaveRepository;
import com.genesis.leavetracker.models.Employee;
import com.genesis.leavetracker.models.EmployeesOnLeave;
import com.genesis.leavetracker.models.LeaveData;

@Component
public class CalenderDaoImpl implements CalenderDao {

	@Autowired
	private LeaveRepository leaveRepository;

	@Autowired
	private EmployeeDetailsRepository employeeDetailsRepository;

	DateFormat keyFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public LeaveData getEmployeeLeaveData(String employeeName) {
		return null;
	}

	@Override
	public EmployeesOnLeave getEmployeesOnLeave(Date date) {
		Map<String, Set<String>> leaveTracker = leaveRepository.getLeaveTracker();
		keyFormat.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
		String key = keyFormat.format(date);
		if (leaveTracker.containsKey(key)) {
			List<Employee> employees = new ArrayList<>();
			Set<String> employeeIds = leaveTracker.get(key);
			for (String employeeId : employeeIds) {
				employees.add(employeeDetailsRepository.getEmployeeDetailsMap().get(employeeId));
			}
			return new EmployeesOnLeave(date, employees);
		}
		return null;
	}

	@Override
	public Map<String, List<Date>> getEmployeeLeaveMap(Date startDate, Date endDate) {
		Map<String, List<Date>> leaveDataMap = new HashMap<>();
		keyFormat.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
		Date currentDate = startDate;
		Calendar calender = Calendar.getInstance();
		calender.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
		String key = keyFormat.format(currentDate);
		do {
			if (leaveRepository.getLeaveTracker().containsKey(key)) {
				Set<String> ids = leaveRepository.getLeaveTracker().get(key);

				for (String id : ids) {
					if (null != id) {
						if (leaveDataMap.containsKey(id)) {
							leaveDataMap.get(id).add(currentDate);
						} else {
							List<Date> dates = new ArrayList<>();
							dates.add(currentDate);
							leaveDataMap.put(id, dates);
						}
					}
				}
			}
			calender.setTime(currentDate);
			calender.add(Calendar.DATE, 1);
			currentDate = calender.getTime();
			key = keyFormat.format(currentDate);
		} while (currentDate.before(endDate));

		return leaveDataMap;
	}

	@Override
	public List<Date> getEmployeeLeaveDates(String employeeId, Date startDate, Date endDate) {
		List<Date> leaveDateList = new ArrayList<>();
		keyFormat.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
		Date currentDate = startDate;
		Calendar calender = Calendar.getInstance();
		calender.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
		String key = keyFormat.format(currentDate);

		do {
			if (leaveRepository.getLeaveTracker().containsKey(key)
					&& (leaveRepository.getLeaveTracker().get(key).contains(employeeId))) {
				leaveDateList.add(currentDate);
			}
			calender.setTime(currentDate);
			calender.add(Calendar.DATE, 1);
			currentDate = calender.getTime();
			key = keyFormat.format(currentDate);
		} while (currentDate.before(endDate));
		return leaveDateList;
	}

}
