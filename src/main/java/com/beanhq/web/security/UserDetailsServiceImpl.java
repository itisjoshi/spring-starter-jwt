package com.beanhq.web.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
/**
 * Created by Prathap Manohar Joshi
 */
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
 
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	    	SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ANOTHER");
	    	List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<SimpleGrantedAuthority>();
	    	updatedAuthorities.add(authority);
    		UserDetails userDetails = new BeanUserDetails("username", new BCryptPasswordEncoder().encode("password"), true, true, true, true, updatedAuthorities, 1L, 1L);
        return userDetails;
    }
}