package com.company.registeruser.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.company.registeruser.models.User;

public class MiUserDetails implements UserDetails{

    private String username;
    private String password;
    private boolean active;

    public MiUserDetails(User usuario){
        
        this.username = usuario.getName();
        this.password = usuario.getPassword();
        this.active = usuario.isIsactive();
    }


    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

}
