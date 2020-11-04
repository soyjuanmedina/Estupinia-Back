package com.calculadoradecostes.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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

	@NotBlank
	@Size(max = 120)
	private String type;
	
	@OneToOne(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
	private EsteemedCustomers esteemedCustomers;
	
	@OneToOne(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private Costs costs;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AccountingNote> incomes;

	@ManyToMany(cascade = {CascadeType.ALL})
	@JsonIgnore
	@JoinTable(	name = "user_projects", 
				joinColumns = @JoinColumn(name = "project_id"), 
				inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> users = new HashSet<>();
    
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
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

	public void setIncomes(Set<AccountingNote> incomes) {
		this.incomes = incomes;
	}

	public Set<AccountingNote> getIncomes() {
		return incomes;
	}
	
	public EsteemedCustomers getEsteemedCustomers() {
		return esteemedCustomers;
	}

	public void setEsteemedCustomers(EsteemedCustomers esteemedCustomers) {
		this.esteemedCustomers = esteemedCustomers;
	}

}
