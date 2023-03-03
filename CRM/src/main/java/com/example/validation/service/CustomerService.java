package com.example.validation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.validation.entity.Customer;
import com.example.validation.repository.CustomerRepository;
@Service
public class CustomerService {

	@Autowired
	CustomerRepository cRepo;
	
	public Customer getOne(int id) {
		return cRepo.findById(id).orElse(null);
	}
	
	public List<Customer> getAll() {
		return cRepo.findAll();
	}
	
	public void create(Customer c) {
		cRepo.save(c);
	}
	
	public void update(int id, Customer c) {
		Customer customer = getOne(id);
		if ( customer != null ) {
			customer.setCompanyName(c.getCompanyName());
			customer.setFirstName(c.getFirstName());
			customer.setLastName(c.getLastName());
			customer.setEmail(c.getEmail());
			customer.setAddress(c.getAddress());
			customer.setCity(c.getCity());
			customer.setCountry(c.getCountry());
			customer.setPhone(c.getPhone());
			customer.setZipCode(c.getZipCode());
			customer.setState(c.getState());
			cRepo.save(customer);
		}
	}
	
	public void delete (Customer c) {
		cRepo.delete(c);
	}
	
	//3
	public Customer getByEmail(String email) {
		return cRepo.findByEmail(email).orElse(null);
	}
	
	//6
	public List<String> getAllCustomersEmails() {
		return  cRepo.getAllCustomersEmails();
	}
	
	public List<Integer> listOfIds() {
		return cRepo.listOfIds();
	}
	

}





