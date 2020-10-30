package com.calculadoradecostes.models;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "costs")
public class Costs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="project_id")
	@JsonIgnore
	private Project project;
	
	private String commentary;
	
//    @OneToMany(mappedBy = "costs", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<AccountingNote> fixed;
//    
//    @OneToMany(mappedBy = "costs", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<AccountingNote> variables;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCommentary() {
		return commentary;
	}

	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}


}
