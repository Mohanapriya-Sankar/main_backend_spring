package edu.training.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.training.model.CustomerServicemodel;
import edu.training.model.Message;
import edu.training.model.Support;
import edu.training.repository.CustomerServiceRepository;
import edu.training.service.CustomerService;
import jakarta.mail.MessagingException;

@RestController
public class CustomerServiceController {

	@Autowired
    private CustomerServiceRepository repo;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private EmailController emailService;

    @CrossOrigin(origins = "http://localhost:4200")
    @ResponseBody
    @PostMapping("/servilogin")
    public ResponseEntity<String> servilogin(@RequestBody Map<String, String> credentialss) {
    	System.out.println(credentialss.get("email"));
        String email = credentialss.get("email");

        String password = credentialss.get("password");
        
 
        CustomerServicemodel cs=repo.findByEmail(credentialss.get("email"));
 

        if (cs == null || !password.equals(cs.getPassword())) {
        		System.out.println(cs.getPassword());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        }

 

        // User is authenticated

        // You can generate a JWT token or create a session here

 

        return ResponseEntity.ok().body("{\"message\": \"Authentication successfull\"}");
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/email/{id}")
    public ResponseEntity<Message>sendEmail(@PathVariable String id) throws MessagingException {
        Support customer = customerService.getCustomerById(id);
        if (customer == null) {
            return new ResponseEntity<Message>(new Message("Customer not found with ID: " + id),HttpStatus.OK);
        }
        // Send email using emailService
        if(Integer.parseInt(customer.getDays())==0)
        {
        	String emailContent = "Hello " +customer.getName()+", Your plan is going to end today. Kindly pay your bill as soon as Possible";
        	emailService.sendEmail(customer.getEmail(),"Subject: Postpaid Notification",emailContent);
        }else
        {
        String emailContent = "Hello " +customer.getName()+", Your plan has ended "+customer.getDays()+" days ago.Kindly pay your bill as soon as Possible";
        emailService.sendEmail(customer.getEmail(),"Subject: Postpaid Notification",emailContent);}
        
        System.out.println(customer);
                return new ResponseEntity<Message>(new Message("Email sent successfully to customer with ID:"+ id),HttpStatus.OK);
            }

}
