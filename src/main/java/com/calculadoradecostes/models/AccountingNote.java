package com.calculadoradecostes.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "accountingNotes")
public class AccountingNote {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;

	private Integer amount;
	
	private String commentary;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_en_acnote_id", referencedColumnName="id")
    @JsonIgnore
    private Project project;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fixedcost_id", referencedColumnName="id")
    @JsonIgnore
    private Costs fixedcost;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "variablecost_id", referencedColumnName="id")
    @JsonIgnore
    private Costs variablecost;

    
    public Costs getFixedcost() {
		return fixedcost;
	}

	public void setFixedcost(Costs fixedcost) {
		this.fixedcost = fixedcost;
	}

	public Costs getVariablecost() {
		return variablecost;
	}

	public void setVariablecost(Costs variablecost) {
		this.variablecost = variablecost;
	}

	public Project getProject() {
			return project;
		}

	public void setProject(Project project) {
		this.project = project;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getCommentary() {
		return commentary;
	}

	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}
	
}
