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
    @JoinColumn(name = "project_id", referencedColumnName="id")
    @JsonIgnore
    private Project project;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fixedcosts_id", referencedColumnName="id")
    @JsonIgnore
    private Costs fixedcosts;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "variablescosts_id", referencedColumnName="id")
    @JsonIgnore
    private Costs variablescosts;

	

	public Costs getFixedcosts() {
		return fixedcosts;
	}

	public void setFixedcosts(Costs fixedcosts) {
		this.fixedcosts = fixedcosts;
	}

	public Costs getVariablescosts() {
		return variablescosts;
	}


	
	public void setVariablescosts(Costs variablescosts) {
	    //prevent endless loop
	    if (sameAsNow(variablescosts))
	      return ;
	    //set new owner
	    Costs oldOwner = this.variablescosts;
	    this.variablescosts = variablescosts;
	    //remove from the old owner
	    if (oldOwner!=null)
	      oldOwner.removeVariablecost(this);
	    //set myself into new owner
	    if (variablescosts!=null)
	    	variablescosts.addVariablecost(this);
	  }
	
	  private boolean sameAsNow(Costs newOwner) {
		    return variablescosts==null? newOwner == null : variablescosts.equals(newOwner);
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
