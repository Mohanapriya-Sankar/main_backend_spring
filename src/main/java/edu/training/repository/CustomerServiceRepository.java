package edu.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.training.model.CustomerServicemodel;
import edu.training.model.Support;
import java.util.List;


public interface CustomerServiceRepository extends JpaRepository<CustomerServicemodel, Long> {
	
		CustomerServicemodel findByEmail(String email);
}
