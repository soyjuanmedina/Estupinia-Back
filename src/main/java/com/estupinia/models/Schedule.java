package com.estupinia.models;

import java.util.HashSet;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Schedules")
public class Schedule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
    private List<ScheduleDay> days;

	@OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
    private List<ScheduleException> exceptions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ScheduleDay> getDays() {
		return days;
	}

	public void setDays(List<ScheduleDay> days) {
		this.days = days;
	}

	public List<ScheduleException> getExceptions() {
		return exceptions;
	}

	public void setExceptions(List<ScheduleException> exceptions) {
		this.exceptions = exceptions;
	}
	
	

}
