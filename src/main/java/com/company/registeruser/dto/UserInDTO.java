package com.company.registeruser.dto;

import java.util.ArrayList;
import java.util.List;

public class UserInDTO extends EntidadDTO {
	
    private String name;
    private String email;
    private String password;
    private List<PhoneDTO> phones = new ArrayList<>();
	
    
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public List<PhoneDTO> getPhones() {
		return phones;
	}
	public void setPhones(List<PhoneDTO> phones) {
		this.phones = phones;
	}
	
	
	@Override
	public String toString() {
		return "UserInDTO [name=" + name + ", email=" + email + ", password=" + password + ", phones=" + phones + "]";
	}
    
    

}
