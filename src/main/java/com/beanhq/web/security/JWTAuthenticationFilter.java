package com.beanhq.web.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Prathap Manohar Joshi
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;


	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		setFilterProcessesUrl("/api/auth/login");
	}
	
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            AuthModel creds = new ObjectMapper()
                    .readValue(req.getInputStream(), AuthModel.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

		ObjectMapper mapper = new ObjectMapper();
		BeanUserDetails beanUserDetails = (BeanUserDetails) auth.getPrincipal();

		List<String> roles = new ArrayList<>();
		@SuppressWarnings("unchecked")
		List<SimpleGrantedAuthority> simpleGrantedAuthorities = (List<SimpleGrantedAuthority>) beanUserDetails.getAuthorities();
		for(SimpleGrantedAuthority simpleGrantedAuthority : simpleGrantedAuthorities) {
			roles.add(simpleGrantedAuthority.getAuthority());
		}
		beanUserDetails.setAuthorities(null);
		beanUserDetails.setRoles(roles);
    		String subject = mapper.writeValueAsString(beanUserDetails);
        String token = Jwts.builder()
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET.getBytes())
                .compact();
        res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
    }
}