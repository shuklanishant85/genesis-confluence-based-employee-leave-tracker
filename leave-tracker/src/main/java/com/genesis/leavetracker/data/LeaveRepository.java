package com.genesis.leavetracker.data;

import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.genesis.leavetracker.models.Employee;
import com.genesis.leavetracker.models.Event;
import com.genesis.leavetracker.models.EventData;

@Repository
@PropertySource("classpath:configuration.properties")
public class LeaveRepository {

	@Autowired
	private RestTemplatePool restTemplatePool;
	@Autowired
	private EmployeeDetailsRepository employeeDetailsRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDetailsRepository.class);

	@Value("${api.confluence.username}")
	private String username;
	@Value("${api.confluence.password}")
	private String password;
	@Value("${api.subcalendar.id}")
	private String subcalender;
	@Value("${api.user.timezone.id}")
	private String timezone;
	@Value(value = "${api.confluence.endpoint}")
	private String confluenceEndpointUri;

	private static final String START_DATE = "&start=";
	private static final String END_DATE = "&end=";
	private static final String SUB_CALENDER_ID = "subCalendarId=";
	private static final String USER_TIME_ZONE_ID = "&userTimeZoneId";

	DateFormat eventDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	DateFormat keyFormat = new SimpleDateFormat("yyyy-MM-dd");
	Map<String, Set<String>> leaveTracker;

	public Map<String, Set<String>> getLeaveTracker() {
		if (null == leaveTracker) {
			this.initialize();
		}
		return leaveTracker;
	}

	@PostConstruct
	public void initialize() {
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
		if (null == leaveTracker) {
			leaveTracker = new HashMap<>();
			Calendar calendar = Calendar.getInstance();
			Date startDate = new Date();
			calendar.setTime(startDate);
			calendar.add(Calendar.MONTH, -6);
			startDate = calendar.getTime();
			calendar.add(Calendar.MONTH, 12);
			Date endDate = calendar.getTime();
			String startDateString = dateFormat.format(startDate);
			String endDateString = dateFormat.format(endDate);
			if (!StringUtils.isEmpty(subcalender)) {
				String[] calenderIdList = subcalender.split(",");
				for (String calendarId : calenderIdList) {
					EventData events = getEventForDuration(startDateString, endDateString, calendarId);
					if (null != events) {
						generateRepositoryCache(events);
					}
				}
			}

		}
	}

	private void generateRepositoryCache(EventData events) {
		List<Event> eventList = events.getEvents();
		if (null != eventList) {
			for (Event event : eventList) {
				Employee employee = generateEmployee(event);
				String startDate = event.getOriginalStartDateTime();
				String endDate = event.getOriginalEndTime();
				Date startDateNumeric = null;
				Date endDateNumeric = null;
				try {
					startDateNumeric = eventDateFormat.parse(startDate);
					endDateNumeric = eventDateFormat.parse(endDate);

				} catch (ParseException e) {
					LOGGER.error("cannot parse the date format : {} : {}", startDate, endDate);
				}
				Calendar calendar = Calendar.getInstance();
				if (null != endDateNumeric && null != startDateNumeric) {
					Date currentDate = startDateNumeric;
					do {
						String key = keyFormat.format(currentDate);
						if (leaveTracker.containsKey(key)) {
							leaveTracker.get(key).add(employee.getId());
						} else {
							Set<String> employeeSet = new HashSet<>();
							employeeSet.add(employee.getId());
							leaveTracker.put(key, employeeSet);
						}
						calendar.setTime(currentDate);
						calendar.add(Calendar.DATE, 1);
						currentDate = calendar.getTime();
					} while (currentDate.before(endDateNumeric));
				}
			}
		}

	}

	private Employee generateEmployee(Event event) {
		Employee employee = new Employee();
		if (!CollectionUtils.isEmpty(event.getInvitees())) {
			if (null != event.getInvitees().get(0).getName()) {
				employee.setId(event.getInvitees().get(0).getName());
			}
			if (null != event.getInvitees().get(0).getDisplayName()) {
				employee.setName(event.getInvitees().get(0).getDisplayName());
			}
			if (null != event.getInvitees().get(0).getEmail()) {
				employee.setEmail(event.getInvitees().get(0).getEmail());
			}
			if (!employeeDetailsRepository.getEmployeeDetailsMap().containsKey(employee.getId())) {
				employeeDetailsRepository.getEmployeeDetailsMap().put(employee.getId(), employee);
			}
		}

		return employee;
	}

	public EventData getEventForDuration(String startDate, String endDate, String subCalenderId) {
		try {
			ResponseEntity<EventData> response = restTemplatePool.restTemplate().exchange(
					generateApiURI(startDate, endDate, subCalenderId), HttpMethod.GET,
					new HttpEntity<Object>(createHeaders(username, password)), EventData.class);
			if (response.getStatusCode() == HttpStatus.OK) {
				return response.getBody();
			}
			LOGGER.error("could not fetch data for calender id: {} | Status code: {}", subCalenderId,
					response.getStatusCode());

		} catch (Exception e) {
			LOGGER.error("couldnot fetch data for calender id : {}", subCalenderId);
		}
		return null;
	}

	HttpHeaders createHeaders(String username, String password) {
		return new HttpHeaders() {
			private static final long serialVersionUID = -5951058538205499679L;
			{
				String auth = username + ":" + password;
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.US_ASCII));
				String authHeader = "Basic " + new String(encodedAuth);
				set("Authorization", authHeader);
			}
		};
	}

	private String generateApiURI(String startDate, String endDate, String subCalenderId) {
		StringBuilder builder = new StringBuilder();
		builder.append(confluenceEndpointUri).append(SUB_CALENDER_ID).append(subCalenderId).append(USER_TIME_ZONE_ID)
		.append(timezone).append(START_DATE).append(startDate).append(END_DATE).append(endDate);
		return builder.toString();
	}
}
