package edu.training.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.training.model.Admin;
import edu.training.repository.AdminRepository;

@RestController
public class AdminController {
	
@Autowired

	    private AdminRepository repo;

	    @CrossOrigin(origins = "http://localhost:4200")

@ResponseBody

	    @PostMapping("/login")

	    public ResponseEntity<String> login(@RequestBody Map<String, String> credentials) {

	        String username = credentials.get("username");

	        String password = credentials.get("password");

	 

	       Admin  ad = repo.findByusername(username);

	 

	        if (ad == null || !password.equals(ad.getPassword())) {

	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

	        }

	 

	        // User is authenticated

	        // You can generate a JWT token or create a session here

	 

	        return ResponseEntity.ok().body("{\"message\": \"Authentication successful\"}");

	    }

	}


	

