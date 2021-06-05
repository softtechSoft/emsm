package com.softtech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class EmsmApplication  extends SpringBootServletInitializer{
	  @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(EmsmApplication.class);
	    }

	public static void main(String[] args) {
		SpringApplication.run(EmsmApplication.class, args);
	}

}
