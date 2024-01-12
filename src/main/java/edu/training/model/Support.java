package edu.training.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity

public class Support {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String name;
	private String mobile;
	private String email;
	private Date duedate;
	private String balance;
	private String currentbill;
	private String previousbill;
	private String days;
	private String type;
	private String installmentamount;

}
