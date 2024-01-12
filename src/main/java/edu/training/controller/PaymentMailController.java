package edu.training.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.training.model.Message;
import edu.training.model.Support;
import edu.training.repository.IssueRepository;
import edu.training.repository.PaymentRepository;
import edu.training.service.CustomerService;
import jakarta.mail.MessagingException;

@RestController
public class PaymentMailController {
	
	@Autowired
	private IssueRepository payrepo;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private EmailController emailService;
	
	    @CrossOrigin(origins = "http://localhost:4200")
	    @PostMapping("/issues/{id}")
	    public ResponseEntity<Message>sendEmail(@PathVariable String id) throws MessagingException {
		 Support customer = customerService.getCustomerById(id);
		 if (customer == null) {
	            return new ResponseEntity<Message>(new Message("Customer not found with ID: " + id),HttpStatus.OK);
	        }
	        else
	        {
	        	String emailContent= "Dear "+customer.getName()+",\n\n"
	                    + "I hope this message finds you well. I wanted to provide you with an update on the status of the issues you've reported. We understand how important it is to have these concerns addressed, and I want to assure you that your satisfaction is our top priority.Our team has been diligently working to resolve the issues you brought to our attention. I'm pleased to inform you that significant progress has been made, and we are in the final stages of resolving them. We anticipate that your concerns will be fully addressed very soon.\n\n" +
                        "If you have any further questions or need additional assistance in the meantime, please don't hesitate to reach out to our customer support team.\n\n" +
                        "Warm regards,\n" +
                        "Netsettle@gmail.com";
	        	emailService.sendEmail(customer.getEmail(),"Subject: Netsettle Customer Service ",emailContent);
	        }
	        
	        System.out.println(customer);
	                return new ResponseEntity<Message>(new Message("Email sent successfully to customer with ID:"+ id),HttpStatus.OK);
	            }
	    
	    @CrossOrigin(origins = "http://localhost:4200")
	    @PostMapping("/paymail/{id}")
	    public ResponseEntity<Message>paymail(@PathVariable String id) throws MessagingException {
		 Support customer = customerService.getCustomerById(id);
		 if (customer == null) {
	            return new ResponseEntity<Message>(new Message("Customer not found with ID: " + id),HttpStatus.OK);
	        }
	        else
	        {
	        	String emailContent= "Dear "+customer.getName()+",\n\n"
	        			   +"We are writing to confirm that your recent bill payment has been received. Your payment has been successfully processed.\n\n" +
	                       "Thank you for your prompt payment.\n\n" +
	                       "Sincerely,\n" +
	                       "Netsettle";
	        	emailService.sendEmail(customer.getEmail(),"Subject:Payment Confirmation ",emailContent);
	        }
	        
	        System.out.println(customer);
	                return new ResponseEntity<Message>(new Message("Payment Confirmation Email sent successfully to customer with ID:"+ id),HttpStatus.OK);
	            }
	    
	    @CrossOrigin(origins = "http://localhost:4200")
	    @PostMapping("/installmentmail/{id}")
	    public ResponseEntity<Message>installmentmail(@PathVariable String id) throws MessagingException {
		 Support customer = customerService.getCustomerById(id);
		 if (customer == null) {
	            return new ResponseEntity<Message>(new Message("Customer not found with ID: " + id),HttpStatus.OK);
	        }
	        else
	        {
	        	String emailContent= "Dear "+customer.getName()+",\n\n"
	        			   +"We are writing to confirm that your recent bill payment of &#x20B9; "+customer.getInstallmentamount()+" has been received. Your payment has been successfully processed.\n\n" +
	                       "Thank you for your prompt payment.\n\n" +
	                       "Sincerely,\n" +
	                       "Netsettle";
	        	emailService.sendEmail(customer.getEmail(),"Subject:Payment Confirmation ",emailContent);
	        }
	        
	        System.out.println(customer);
	                return new ResponseEntity<Message>(new Message("Payment Confirmation Email sent successfully to customer with ID:"+ id),HttpStatus.OK);
	            }
	

}
