package com.genesis.leavetracker.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties
public class Event {

	@JsonProperty("description")
	private String description;
	@JsonProperty("className")
	private String className;
	@JsonProperty("shortTitle")
	private String shortTitle;
	@JsonProperty("title")
	private String title;
	@JsonProperty("localizedStartDate")
	private String localizedStartDate;
	@JsonProperty("invitees")
	private List<Invitee> invitees = null;
	@JsonProperty("localizedEndDate")
	private String localizedEndDate;
	@JsonProperty("originalStartDateTime")
	private String originalStartDateTime;
	@JsonProperty("originalEndDateTime")
	private String originalEndTime;
	@JsonProperty("editable")
	private boolean editable;
	@JsonProperty("start")
	private String start;
	@JsonProperty("originalStartTime")
	private String originalStartTime;
	@JsonProperty("eventType")
	private String eventType;
	@JsonProperty("subCalendarId")
	private String subCalendarId;
	@JsonProperty("localizedOriginalStartDate")
	private String localizedOriginalStartDate;
	@JsonProperty("localizedOriginalEndDate")
	private String localizedOriginalEndDate;

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty("className")
	public String getClassName() {
		return className;
	}

	@JsonProperty("className")
	public void setClassName(String className) {
		this.className = className;
	}

	@JsonProperty("shortTitle")
	public String getShortTitle() {
		return shortTitle;
	}

	@JsonProperty("shortTitle")
	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	@JsonProperty("title")
	public String getTitle() {
		return title;
	}

	@JsonProperty("title")
	public void setTitle(String title) {
		this.title = title;
	}

	@JsonProperty("localizedStartDate")
	public String getLocalizedStartDate() {
		return localizedStartDate;
	}

	@JsonProperty("localizedStartDate")
	public void setLocalizedStartDate(String localizedStartDate) {
		this.localizedStartDate = localizedStartDate;
	}

	@JsonProperty("invitees")
	public List<Invitee> getInvitees() {
		return invitees;
	}

	@JsonProperty("invitees")
	public void setInvitees(List<Invitee> invitees) {
		this.invitees = invitees;
	}

	@JsonProperty("localizedEndDate")
	public String getLocalizedEndDate() {
		return localizedEndDate;
	}

	@JsonProperty("localizedEndDate")
	public void setLocalizedEndDate(String localizedEndDate) {
		this.localizedEndDate = localizedEndDate;
	}

	@JsonProperty("originalStartDateTime")
	public String getOriginalStartDateTime() {
		return originalStartDateTime;
	}

	@JsonProperty("originalStartDateTime")
	public void setOriginalStartDateTime(String originalStartDateTime) {
		this.originalStartDateTime = originalStartDateTime;
	}

	@JsonProperty("originalEndTime")
	public String getOriginalEndTime() {
		return originalEndTime;
	}

	@JsonProperty("originalEndTime")
	public void setOriginalEndTime(String originalEndTime) {
		this.originalEndTime = originalEndTime;
	}

	@JsonProperty("editable")
	public boolean isEditable() {
		return editable;
	}

	@JsonProperty("editable")
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	@JsonProperty("start")
	public String getStart() {
		return start;
	}

	@JsonProperty("start")
	public void setStart(String start) {
		this.start = start;
	}

	@JsonProperty("originalStartTime")
	public String getOriginalStartTime() {
		return originalStartTime;
	}

	@JsonProperty("originalStartTime")
	public void setOriginalStartTime(String originalStartTime) {
		this.originalStartTime = originalStartTime;
	}

	@JsonProperty("eventType")
	public String getEventType() {
		return eventType;
	}

	@JsonProperty("eventType")
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	@JsonProperty("subCalendarId")
	public String getSubCalendarId() {
		return subCalendarId;
	}

	@JsonProperty("subCalendarId")
	public void setSubCalendarId(String subCalendarId) {
		this.subCalendarId = subCalendarId;
	}

	@JsonProperty("localizedOriginalStartDate")
	public String getLocalizedOriginalStartDate() {
		return localizedOriginalStartDate;
	}

	@JsonProperty("localizedOriginalStartDate")
	public void setLocalizedOriginalStartDate(String localizedOriginalStartDate) {
		this.localizedOriginalStartDate = localizedOriginalStartDate;
	}

	@JsonProperty("localizedOriginalEndDate")
	public String getLocalizedOriginalEndDate() {
		return localizedOriginalEndDate;
	}

	@JsonProperty("localizedOriginalEndDate")
	public void setLocalizedOriginalEndDate(String localizedOriginalEndDate) {
		this.localizedOriginalEndDate = localizedOriginalEndDate;
	}

}