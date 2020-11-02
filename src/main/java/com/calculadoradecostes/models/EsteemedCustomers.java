package com.calculadoradecostes.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "esteemedCustomers")
public class EsteemedCustomers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer esteemed;
	
	private String commentary;
	
	private Integer averageTicket;
	
	private boolean monthly;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="project_id", referencedColumnName="id")
	@JsonIgnore
	private Project project;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getEsteemed() {
		return esteemed;
	}

	public void setEsteemed(Integer esteemed) {
		this.esteemed = esteemed;
	}

	public String getCommentary() {
		return commentary;
	}

	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}

	public Integer getAverageTicket() {
		return averageTicket;
	}

	public void setAverageTicket(Integer averageTicket) {
		this.averageTicket = averageTicket;
	}

	public boolean isMonthly() {
		return monthly;
	}

	public void setMonthly(boolean monthly) {
		this.monthly = monthly;
	}
	
	public Project getProject() {
		return project;
	}
	
	public void setProject(Project project) {
		this.project = project;
	}

}
