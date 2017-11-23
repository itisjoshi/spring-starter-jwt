package com.beanhq.web.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.beanhq.web.security.BeanUserDetails;


/**
 * Created by Prathap Manohar Joshi.
 */
@RestController
public class HomeController {
	
	@Secured(value = {"BEAN_ADMIN"})
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Map<String, String> home(Locale locale) {
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		System.out.println("Server Started, Home Called Succcessfully");
		Map<String, String> object = new HashMap<String, String>();
		object.put("Company : ", "Bean HQ");
		object.put("Team : ", "Bean HQ - Intelligent Assistant");
		object.put("Git Hub URL : ", "https://bitbucket.com/beanhq");
		return object;
	}
	
}
