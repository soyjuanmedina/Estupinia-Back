package com.columns.payload.request;

import java.util.Set;

import javax.validation.constraints.*;
 
public class SignupRequest {

    @Size(max = 50)
    private String name;
 
    @Size(max = 50)
    private String surname;
    
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    
    private Set<String> role;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
  
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }    

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Set<String> getRole() {
      return this.role;
    }
    
    public void setRole(Set<String> role) {
      this.role = role;
    }
}
