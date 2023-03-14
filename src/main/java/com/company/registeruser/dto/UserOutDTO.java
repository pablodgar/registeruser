package com.company.registeruser.dto;

import java.util.Date;


public class UserOutDTO extends EntidadDTO {
	
	private String id;
	private Date created;
	private Date modified;
	private Date last_login;
	private String token;
	private boolean isactive;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	
	
	@Override
	public String toString() {
		return "UserOutDTO [id=" + id + ", created=" + created + ", modified=" + modified + ", last_login=" + last_login
				+ ", token=" + token + ", isactive=" + isactive + "]";
	}
	
	
	
	

}
