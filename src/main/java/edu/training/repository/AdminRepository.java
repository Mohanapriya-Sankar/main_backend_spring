package edu.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import edu.training.model.Admin;

public interface AdminRepository extends JpaRepository<Admin,String> {
	Admin findByusername (String username);

}
