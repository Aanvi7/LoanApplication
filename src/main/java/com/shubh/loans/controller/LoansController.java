package com.shubh.loans.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.shubh.loans.config.LoanConfigServer;
import com.shubh.loans.model.Customer;
import com.shubh.loans.model.Loans;
import com.shubh.loans.model.Properties;
import com.shubh.loans.repository.LoansRepository;

@RestController
public class LoansController {
	private static final Logger logger = LoggerFactory.getLogger(LoansController.class);

	@Autowired
	private LoansRepository loansRepository;
	
	@Autowired
	private LoanConfigServer configServer;
	
	@PostMapping("/myLoans")
	public List<Loans> getLoansDetails(@RequestBody Customer customer) {
		logger.info("getLoansDetails () method started");
		List<Loans> loans = loansRepository.findByCustomerIdOrderByStartDtDesc(customer.getCustomerId());
		logger.info("getLoansDetails () method ended");

		if (loans != null) {
			return loans;
		} else {
			return null;
		}

	}
	
	@GetMapping("/loan/properties")
	public String getPropertyDetails() throws JsonProcessingException{
		ObjectWriter objectWriter=new ObjectMapper().writer().withDefaultPrettyPrinter();
		com.shubh.loans.model.Properties properties=new Properties(configServer.getMsg(), configServer.getBuildVersion(), configServer.getMailDetails(), configServer.getActiveBranches());
		String jsonStr=objectWriter.writeValueAsString(properties);
		
		return jsonStr;
	}


}
