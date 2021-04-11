package com.calculadoradecostes.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(	name = "projects")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 120)
	private String name;
	
	private String uuid;

	@NotBlank
	@Size(max = 120)
	private String type;
	
	@OneToOne(cascade = CascadeType.ALL)
	private EsteemedCustomers esteemedCustomers;
	
	@OneToOne(cascade = CascadeType.ALL)
    private Costs costs;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Collection<AccountingNote> incomes;
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Costs getCosts() {
		return costs;
	}

	public void setCosts(Costs costs) {
		this.costs = costs;
	}
	
	public EsteemedCustomers getEsteemedCustomers() {
		return esteemedCustomers;
	}

	public void setEsteemedCustomers(EsteemedCustomers esteemedCustomers) {
		this.esteemedCustomers = esteemedCustomers;
	}
	
	public void setIncomes(Collection<AccountingNote> incomes) {
		this.incomes = new ArrayList<AccountingNote>();
		for (AccountingNote income : incomes) {
			addIncome(income);
		}
	}

	public Collection<AccountingNote> getIncomes() {
	    return incomes;
	  }

	public void addIncome(AccountingNote income) {
		if (incomes.contains(income))
			return ;
		incomes.add(income);
		income.setProject(this);
	  }

	  public void removeIncome(AccountingNote income) {
	    if (!incomes.contains(income))
	      return ;
	    incomes.remove(income);
	    income.setProject(null);
	  }

	  @PrePersist
	  public void autofill() {
	      this.setUuid(UUID.randomUUID().toString());
	  }
}
