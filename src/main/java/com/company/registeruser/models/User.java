package com.company.registeruser.models;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.company.registeruser.dto.UserOutDTO;


@Entity
@Table(name = "user")
public class User implements Entidad {
    
	@Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private UUID id;
    
    private String name;
    private String email;
    private String password;
    private Date created;
    private Date modified;	
    private Date last_login;
    private String token;
    private boolean isactive;
    
    @OneToMany(mappedBy="user")
    private List<Phone> phones;

	
    public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Date getLast_login() {
		return last_login;
	}

	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", created="
				+ created + ", modified=" + modified + ", last_login=" + last_login + ", token=" + token + ", isactive="
				+ isactive + ", phones=" + phones + "]";
	}

	@Override
	public UserOutDTO toDTO() {
		UserOutDTO dto = new UserOutDTO();
		dto.setId(this.id.toString());
		dto.setCreated(this.created);
		dto.setIsactive(this.isactive);
		dto.setLast_login(this.last_login);
		dto.setModified(this.modified);
		dto.setToken(this.token);
		return dto;
	}
    
    
    
    
}
