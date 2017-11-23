package com.beanhq.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.beanhq.utils.Constants;

/**
 * Created by Prathap Manohar Joshi
 */
@Configuration
@EnableWebMvc
public class ApplicationInitializer implements WebApplicationInitializer {
    public void onStartup(ServletContext servletContext) throws ServletException {
    	
    		servletContext.addListener("org.springframework.web.context.ContextLoaderListener");
    		servletContext.addListener("org.springframework.web.context.request.RequestContextListener");

    		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.setServletContext(servletContext);

        ServletRegistration.Dynamic servlet = servletContext.addServlet(Constants.DISPATCHER, new DispatcherServlet(ctx));
 
        servlet.setLoadOnStartup(1);
        servlet.addMapping(Constants.SLASH);
        servletContext.addFilter("springSecurityFilterChain", org.springframework.web.filter.DelegatingFilterProxy.class).addMappingForUrlPatterns(null, true, Constants.SLASH);
        
    }
 
}