package edu.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.training.model.Support;
import java.util.List;
import java.util.Optional;


public interface PaymentRepository extends JpaRepository<Support, String> {
	
	Optional<Support> findById(String id);


}
