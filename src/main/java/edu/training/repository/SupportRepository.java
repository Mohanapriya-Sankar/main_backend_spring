package edu.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.training.model.Admin;
import edu.training.model.Support;
import java.util.List;
import java.util.Optional;
import java.sql.Date;


@Repository
public interface SupportRepository extends JpaRepository<Support, String> {
	
	List<Support> findByDuedate(Date duedate);
	
	Optional<Support> findById(String id);

	List<Support> findByDays(String days);

	List<Support> findByDaysAfter(String days);
	
	List<Support> findByDaysBefore(String days);
	
	Support findByMobile(String mobile);
	

}
