package edu.training.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.training.model.CustomerServicemodel;
import edu.training.model.Support;
import edu.training.repository.CustomerServiceRepository;
import edu.training.repository.SupportRepository;

@Service
public class CustomerService {
	
//	public List<CustomerServicemodel> getCustomersWithOverdueBills() {
//        LocalDate currentDate = LocalDate.now();
//        return repository.findByDueDateBefore(currentDate);
//    }
	@Autowired
    private SupportRepository repository;
	
    public Support getCustomerById(String id) {
        return repository.findById(id).orElse(null);
    }
    public List<Support> getCustomersWithDueDateToday() {

        Date today =new Date(System.currentTimeMillis());
        System.out.println(today);
        return repository.findByDuedate(today);

    }
    
    public List<Support> getDueCustomers()
    {
    	return repository.findByDays("1");
    }
    
    public List<Support> getDueCustomers1()
    {
    	return repository.findByDaysAfter("3");
    }
    
    public List<Support> getDueCustomers2()
    {
    	return repository.findByDaysBefore("5");
    }

}
