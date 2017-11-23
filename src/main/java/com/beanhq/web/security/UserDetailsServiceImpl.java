package com.beanhq.web.security;

/**
 * Created by Prathap Manohar Joshi
 */
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
 
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	    	SimpleGrantedAuthority authority = new SimpleGrantedAuthority("BEAN_ADMIN");
	    	List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<SimpleGrantedAuthority>();
	    	updatedAuthorities.add(authority);
    		UserDetails userDetails = new BeanUserDetails("username", new BCryptPasswordEncoder().encode("password"), true, true, true, true, updatedAuthorities, 1L, 1L);
        return userDetails;
    }
}