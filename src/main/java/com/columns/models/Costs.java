package com.columns.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "costs")
public class Costs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(mappedBy = "costs", cascade = CascadeType.ALL)
    @JoinColumn(name = "costs_id", referencedColumnName = "id")
	@JsonIgnore
	private Project project;
    
    @OneToMany(mappedBy = "fixedcost", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<AccountingNote> fixedcosts;
    
    @OneToMany(mappedBy = "variablecost", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<AccountingNote> variablescosts;
	

    public void setFixedcosts(Collection<AccountingNote> fixedcosts) {
		this.fixedcosts = new ArrayList<AccountingNote>();
		for (AccountingNote fixedcost : fixedcosts) {
			addFixedcost(fixedcost);
		}
	}

	public Collection<AccountingNote> getFixedcosts() {
	    return new ArrayList<AccountingNote>(fixedcosts);
	  }

	public void addFixedcost(AccountingNote fixedcost) {
		if (fixedcosts.contains(fixedcost))
			return ;
		fixedcosts.add(fixedcost);
		fixedcost.setFixedcost(this);
	  }

	  public void removeFixedcost(AccountingNote fixedcost) {
	    if (!fixedcosts.contains(fixedcost))
	      return ;
	    fixedcosts.remove(fixedcost);
	    fixedcost.setFixedcost(null);
	  }
	  
	   public void setVariablescosts(Collection<AccountingNote> variablescosts) {
			this.variablescosts = new ArrayList<AccountingNote>();
			for (AccountingNote variablecost : variablescosts) {
				addVariablecost(variablecost);
			}
		}

		public Collection<AccountingNote> getVariablescosts() {
		    return new ArrayList<AccountingNote>(variablescosts);
		  }

		public void addVariablecost(AccountingNote variablecost) {
			if (variablescosts.contains(variablecost))
				return ;
			variablescosts.add(variablecost);
			variablecost.setVariablecost(this);
		  }

		  public void removeVariablecost(AccountingNote variablecost) {
		    if (!variablescosts.contains(variablecost))
		      return ;
		    variablescosts.remove(variablecost);
		    variablecost.setVariablecost(null);
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

}
