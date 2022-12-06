package com.shubh.loans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@SpringBootConfiguration
@ComponentScans({ @ComponentScan("com.shubh.loans.controller") })
@EnableJpaRepositories("com.shubh.loans.repository")
@EntityScan("com.shubh.loans.model")
public class LoanApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanApplication.class, args);
		
		System.out.println("Loan  Application is running on 83 port ....");
	}

}
