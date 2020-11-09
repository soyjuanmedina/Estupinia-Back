package com.calculadoradecostes.models;

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
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "project_id", referencedColumnName = "id")
	@JsonIgnore
	private Project project;
    
    @OneToMany(mappedBy = "fixedcosts")
    private Set<AccountingNote> fixedcosts;
    
    @OneToMany(mappedBy = "variablescosts")
    private Set<AccountingNote> variablescosts;
	
	public Set<AccountingNote> getFixedcosts() {
		return fixedcosts;
	}

	public void setFixedcosts(Set<AccountingNote> fixedcosts) {
		this.fixedcosts = fixedcosts;
	}

	public Set<AccountingNote> getVariablescosts() {
		return variablescosts;
	}

	public void setVariablescosts(Set<AccountingNote> variablescosts) {
		this.variablescosts = variablescosts;
	}
	
	public void addVariablecost(AccountingNote variablecost) {
	    //prevent endless loop
	    if (variablescosts.contains(variablecost))
	      return ;
	    //add new account
	    variablescosts.add(variablecost);
	    //set myself into the twitter account
	    variablecost.setVariablescosts(this);
	  }
	  
	  /**
	   * Removes the account from the person. The method keeps 
	   * relationships consistency:
	   * * the account will no longer reference this person as its owner
	   */
	  public void removeVariablecost(AccountingNote variablecost) {
	    //prevent endless loop
	    if (!variablescosts.contains(variablecost))
	      return ;
	    //remove the account
	    variablescosts.remove(variablecost);
	    //remove myself from the twitter account
	    variablecost.setVariablescosts(null);
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
