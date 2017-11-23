package com.beanhq.web.security;

import io.jsonwebtoken.Jwts;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prathap Manohar Joshi
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(SecurityConstants.HEADER_STRING);

        if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
        		res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied");
        		return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);        	
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException {
    		ObjectMapper objectMapper = new ObjectMapper();
        String token = request.getHeader(SecurityConstants.HEADER_STRING);
        if (token != null) {
            // parse the token.
            String jsonString = Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET.getBytes())
                    .parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();
            BeanUserDetails beanUserDetails = objectMapper.readValue(jsonString, BeanUserDetails.class);
	    		List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<SimpleGrantedAuthority>();            
            for(String role: beanUserDetails.getRoles()) {
            		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);            	
            		updatedAuthorities.add(authority);
            }
		    
            beanUserDetails.setAuthorities(updatedAuthorities);
            if (beanUserDetails != null) {
                return new UsernamePasswordAuthenticationToken(beanUserDetails, null, updatedAuthorities);
            }
            return null;
        }
        return null;
    }
}