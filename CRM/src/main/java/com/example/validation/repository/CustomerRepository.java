package com.example.validation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.validation.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	
	//3
	Optional<Customer> findByEmail(String email);
	
	
	//6
	@Query(value = "SELECT email FROM Customer c ")
	List<String> getAllCustomersEmails();
	
	
	@Query(value = "SELECT id FROM Customer c")
	List<Integer> listOfIds();
}
