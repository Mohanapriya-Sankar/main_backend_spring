package edu.training.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.training.model.Admin;
import edu.training.model.Customer;
import edu.training.model.Support;
import edu.training.repository.AdminRepository;
import edu.training.repository.CustomerRepository;
import edu.training.repository.PaymentRepository;
import edu.training.repository.SupportRepository;

@RestController
public class SupportController {
	
	@Autowired
	private SupportRepository repo;
	@Autowired
	private PaymentRepository rep;
	@Autowired
	private CustomerRepository adrepo;
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/pending")
	@ResponseBody
	
	public List<Support> pending()
	{
		return repo.findAll();
	}
	
	@CrossOrigin(origins="http://localhost:4200")
    @PostMapping("/phone")
    @ResponseBody
    public ResponseEntity<Support> phone(@RequestBody Map<String, String> credentials) {
        String mobile = credentials.get("phoneNumber");
       Support  ad = repo.findByMobile(mobile);
System.out.println(ad);
System.out.println(credentials);
System.out.println(mobile);
//System.out.println(ad.getBalance());
        if (ad == null || !mobile.equals(ad.getMobile())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        // User is authenticated
        // You can generate a JWT token or create a session here
        return ResponseEntity.ok(ad);
        // ok().body("{\"message\": \"Authentication successful\"}");
       
  }
	@CrossOrigin(origins="http://localhost:4200")
    @PostMapping("/mobile")
    @ResponseBody
    public ResponseEntity<Customer> mobile(@RequestBody Map<String, String> credentials) {
        String mobile = credentials.get("phoneNumber");
       Customer  ad = adrepo.findByMobile(mobile);
System.out.println(ad);
System.out.println(credentials +"of mobile number");
System.out.println(mobile);
//System.out.println(ad.getBalance());
        if (ad == null || !mobile.equals(ad.getMobile())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        // User is authenticated
        // You can generate a JWT token or create a session here
        return ResponseEntity.ok(ad);
        // ok().body("{\"message\": \"Authentication successful\"}");
       
  }
	
	@CrossOrigin(origins="http://localhost:4200")
    @PostMapping("/paydet")
    @ResponseBody
    public ResponseEntity<Optional<Support>> paydet(@RequestBody String credentials) {
        System.out.println(credentials);
        Optional<Support>  ad = rep.findById(credentials);
        System.out.println(ad);
	        return ResponseEntity.ok(ad);
    }
	@CrossOrigin(origins="http://localhost:4200")
    @PostMapping("/installmentamount")
    @ResponseBody
    public ResponseEntity<Optional<Support>> installmentamount(@RequestBody String credentials) {
		String ad=credentials;
		Optional<Support> details=rep.findById(ad);
        return ResponseEntity.ok(details);
  }
	
	@CrossOrigin(origins="http://localhost:4200")
    @PostMapping("/updateinstallment")
    @ResponseBody
    public ResponseEntity<Support> updateinstallment(@RequestBody Support credentials) {
		Support ad=credentials;
        repo.save(ad);
        return ResponseEntity.ok(ad);
  }
	
	@CrossOrigin(origins="http://localhost:4200")
    @PostMapping("/paid")
    @ResponseBody
    public ResponseEntity<String> paid(@RequestBody String credentials) {
        System.out.println(credentials);
        rep.deleteById(credentials); 
	     return ResponseEntity.ok().body("{\"message\": \"Deleted Successfully\"}");
    }
	
	@CrossOrigin(origins="http://localhost:4200")
    @PostMapping("/admin")
    @ResponseBody
    public ResponseEntity<Optional<Customer>> admin(@RequestBody String credentials) {
        System.out.println("Work find");
        System.out.println(credentials);
        Optional<Customer> ad=adrepo.findById(credentials);
        System.out.println("Work");
        System.out.println(ad);
	     return ResponseEntity.ok(ad);
    }
	@CrossOrigin(origins="http://localhost:4200")
    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<Customer> update(@RequestBody Customer credentials) {
		Customer ad=credentials;
        adrepo.save(ad);
        System.out.println("Payment Update"+ad);
        return ResponseEntity.ok(ad);
        // ok().body("{\"message\": \"Authentication successful\"}");
       
  }
	
	@CrossOrigin(origins="http://localhost:4200")
    @PostMapping("/updatePending")
    @ResponseBody
    public ResponseEntity<Support> updatePending(@RequestBody Support credentials) {
		Support ad=credentials;
        repo.save(ad);
        return ResponseEntity.ok(ad);
        // ok().body("{\"message\": \"Authentication successful\"}");
       
  }
	
	@CrossOrigin(origins="http://localhost:4200")
    @PostMapping("/support")
    @ResponseBody
    public ResponseEntity<Optional<Support>> support(@RequestBody String credentials) {
        System.out.println("support");
        System.out.println(credentials);
        Optional<Support> ad=repo.findById(credentials);
        System.out.println("Hello");
        System.out.println(ad);
	     return ResponseEntity.ok(ad);
    }
	
}
