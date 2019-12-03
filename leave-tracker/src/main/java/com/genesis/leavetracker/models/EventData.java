package com.genesis.leavetracker.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties
public class EventData {

	@JsonProperty("success")
	private boolean success;
	@JsonProperty("events")
	private List<Event> events;

	@JsonProperty("success")
	public boolean isSuccess() {
		return success;
	}

	@JsonProperty("success")
	public void setSuccess(boolean success) {
		this.success = success;
	}

	@JsonProperty("events")
	public List<Event> getEvents() {
		return events;
	}

	@JsonProperty("events")
	public void setEvents(List<Event> events) {
		this.events = events;
	}

}