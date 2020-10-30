package com.calculadoradecostes.models;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "projects")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 120)
	private String name;
	
	@OneToOne(mappedBy="project", cascade = CascadeType.ALL, orphanRemoval = true)
	private EsteemedCustomers esteemedCustomers;
	
	@OneToOne(mappedBy="project", cascade = CascadeType.ALL, orphanRemoval = true)
	private Costs costs;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AccountingNote> incomes;


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
	
	public EsteemedCustomers getEsteemedCustomers() {
		return esteemedCustomers;
	}

	public void setEsteemedCustomers(EsteemedCustomers esteemedCustomers) {
		this.esteemedCustomers = esteemedCustomers;
	}
	
	public Costs getCosts() {
		return costs;
	}

	public void setCosts(Costs costs) {
		this.costs = costs;
	}

	public void setIncomes(Set<AccountingNote> incomes) {
		this.incomes = incomes;
	}

	public Set<AccountingNote> getIncomes() {
		return incomes;
	}

}
