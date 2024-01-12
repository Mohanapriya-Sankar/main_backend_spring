package edu.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.training.model.Customer;
import edu.training.repository.CustomerRepository;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerRepository repo;
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/view")
	@ResponseBody
	
	public List<Customer> view()
	{
		return repo.findAll();
	}
	
	
	
	

}
