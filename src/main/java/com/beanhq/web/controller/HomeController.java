package com.beanhq.web.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by Prathap Manohar Joshi.
 */
@RestController
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('BEAN_ADMIN')")
	public Map<String, String> home(Locale locale) {
		System.out.println("Server Started, Home Called Succcessfully");
		Map<String, String> object = new HashMap<String, String>();
		object.put("Company : ", "Bean HQ");
		object.put("Team : ", "Bean HQ - Intelligent Assistant");
		object.put("Git Hub URL : ", "https://bitbucket.com/beanhq");
		return object;
	}

	@RequestMapping(value = "/dummy", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public Map<String, String> wdw(Locale locale) {
		System.out.println("Server Started, Home Called Succcessfully");
		Map<String, String> object = new HashMap<String, String>();
		object.put("Company : ", "Bean HQ");
		object.put("Team : ", "Bean HQ - Intelligent Assistant");
		object.put("Git Hub URL : ", "https://bitbucket.com/beanhq");
		return object;
	}
	
}
