package edu.training.sceduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import edu.training.controller.EmailController;
import edu.training.model.Support;
import edu.training.service.CustomerService;
import jakarta.mail.MessagingException;

@Component
public class EmailScheduler {
	
	private final CustomerService customerService;
    private final EmailController emailService;

    @Autowired

    public EmailScheduler(CustomerService customerService, EmailController emailService) {

        this.customerService = customerService;

        this.emailService = emailService;

    }
    @Scheduled(cron = "0 17 14 * * ?") // This schedules the task to run every day at midnight
    public void sendDueDateReminders() {
    	System.out.println("Today");

        List<Support> customers = customerService.getCustomersWithDueDateToday();

        for (Support customer : customers) {

            String emailContent1 = "<html><body>Dear Customer, Your Postpaid Plan is going to end Today.Recharge IMMEDIATELY for uninterrupted services. <form action='http://localhost:4200/totalpayment'><br><br><input type='submit' value='Pay Now' style=\"background-color:  coral;\"></form></body></html>";
            try {
                emailService.sendEmail(customer.getEmail(), "Payment Reminder", emailContent1);
            	} catch (MessagingException e) {

                // Handle email sending exception (log or rethrow if necessary)

                e.printStackTrace();

            }

        }

    }
    @Scheduled(cron = "0 16 14 * * ?")
    public void remainders()
    {
    	System.out.println("Yesterday");
    	List<Support> remain=customerService.getDueCustomers();
    	for (Support customer : remain) {

//            String emailContent1 = "<html><body>Ended 1 days ago <form action='http://localhost:4200/pay'><input type='submit' value='pay'></form></body></html>";
            String emailContent1 = "<html><body>Dear Customer, Your Postpaid Plan expired on Yesterday ("+customer.getDuedate()+").Recharge IMMEDIATELY to continue our services. <form action='http://localhost:4200/totalpayment'><br><br><input type='submit' value='Pay Now' style=\"background-color:  coral;\"></form></body></html>";

            try {
                emailService.sendEmail(customer.getEmail(), "Gentle Reminder", emailContent1);
            	} catch (MessagingException e) {

                // Handle email sending exception (log or rethrow if necessary)

                e.printStackTrace();

            }

        }
    	
    }
    @Scheduled(cron = "0 17 14 * * ?")
    public void remainders1()
    {
    	System.out.println("3 Days ago");
    	List<Support> remain=customerService.getDueCustomers1();
    	for (Support customer : remain) {

            String emailContent1 = "<html><body>Dear Customer, Your Postpaid Plan expired 3 days ago ("+customer.getDuedate()+").Recharge IMMEDIATELY to continue our services.<form action='http://localhost:4200/totalpayment'><br><br><input type='submit' value='Pay Now' style=\"background-color:  coral;\"></form></body></html>";
            try {
                emailService.sendEmail(customer.getEmail(), "Final Reminder", emailContent1);
            	} catch (MessagingException e) {

                // Handle email sending exception (log or rethrow if necessary)

                e.printStackTrace();

            }

        }
    	
    }
    @Scheduled(cron = "0 18 01 * * ?")
    public void remainders2()
    {
    	System.out.println("Terminated");
    	List<Support> remain=customerService.getDueCustomers2();
    	for (Support customer : remain) {

            String emailContent1 = "<html><body>Dear " + customer.getName()
            		+",We regret to inform you that your postpaid plan has been terminated. If you have any outstanding payments or need further assistance, please contact our support team.<form action='http://http://localhost:4200/issues'><br><br><input type='submit' value='Customer Support' style=\"background-color:  coral;\"></form></body></html>";
            try {
                emailService.sendEmail(customer.getEmail(), "Terminated Reminder", emailContent1);
            	} catch (MessagingException e) {

                // Handle email sending exception (log or rethrow if necessary)

                e.printStackTrace();

            }

        }
    	
    }

}
