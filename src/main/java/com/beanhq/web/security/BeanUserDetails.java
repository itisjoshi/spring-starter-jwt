package com.beanhq.web.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Collection;
import java.util.List;

/**
 * Created by Prathap Manohar Joshi
 */
@Service
@JsonIgnoreProperties(ignoreUnknown = true)
public class BeanUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;

	private Long userId;
	private Boolean accountNonExpired;
	private Boolean accountNonLocked;
	private Boolean credentialsNonLocked;
	private Boolean enabled;
	private List<SimpleGrantedAuthority> authorities;
	private List<String> roles;

	public List<String> getRoles() {
		return roles;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	private Long orgId;
	
	public BeanUserDetails() {
		// TODO Auto-generated constructor stub
	}

	public BeanUserDetails(String username, String password,
			Boolean accountNonExpired, Boolean accountNonLocked,
			Boolean credentialsNonLocked, Boolean enabled,
			List<SimpleGrantedAuthority> authorities, Long orgId,
			Long userId) {
		// TODO Auto-generated constructor stub

		this.username = username;
		this.password = password;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonLocked = credentialsNonLocked;
		this.enabled = enabled;
		this.authorities = authorities;
		this.orgId = orgId;
		this.userId = userId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public void setAuthorities(List<SimpleGrantedAuthority> authorities) {
		// TODO Auto-generated method stub
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return credentialsNonLocked;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	} 

}