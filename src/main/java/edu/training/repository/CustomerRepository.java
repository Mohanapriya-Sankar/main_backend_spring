package edu.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.training.model.Customer;
import java.util.List;
import java.util.Optional;


public interface CustomerRepository extends JpaRepository<Customer, String> {

	Optional<Customer> findById(String id);
	Customer  findByMobile(String mobile);
}
