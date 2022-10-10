package com.jitpay.user.location;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Scope;

/**
 * @author Ajay Sarvasiddhi
 * This is the driver or an init class. Running this class will produce the appropriate results. 
 */
@SpringBootApplication
@Scope("prototype")
public class Application {

	public static void main(String args[]) {
		SpringApplication.run(Application.class, args);
	}
	
}
