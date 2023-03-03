package com.example.validation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.validation.entity.Customer;
import com.example.validation.entity.Order;
import com.example.validation.service.CustomerService;
import com.example.validation.service.OrderService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerService cService;
	
	@Autowired
	OrderService oService;
	

	@PostMapping("/fake/{nbClients}")
	public List<Customer> fakeCustomers(@PathVariable("nbClients") int nbClients ){
		List <Customer> fakeCustomers = new ArrayList<>();
		for ( int i = 0 ; i < nbClients ; i++ ) {
			Customer c = new Customer();
			cService.create(c);
			fakeCustomers.add(c);
		}
		return fakeCustomers;
	}
	
	@GetMapping
	public List<Customer> findAll(){
		return cService.getAll();
	}
	
	@GetMapping("/{id}")
	public Customer findById(@PathVariable("id") int id) {
		return cService.getOne(id);
	}
	
	@PostMapping
	public void saveCustomer(@RequestBody Customer c) {
		cService.create(c);
	}
	
	@PutMapping("/{id}")
	public void updateCustomer(@PathVariable("id") int id, @RequestBody Customer c) {
		cService.update(id, c);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCustomer(@PathVariable("id") int id) {
		List<Order> listOrdersOfCustomer = oService.findByIdCustomer(id);
		
		for (Order order : listOrdersOfCustomer) {
			oService.delete(order);
		}
		
		cService.delete(cService.getOne(id));
	}
	
	//3
	@GetMapping("/email/{email}")
	public Customer getByEmail(@PathVariable("email") String email){
		return cService.getByEmail(email);
	}
	
	//6
	@GetMapping("/emails")
	public List<String> getAllCustomersEmails() {
		return cService.getAllCustomersEmails();
	}
	@GetMapping("/ids")
	public List<Integer> listOfIds() {
		return cService.listOfIds();
	}
	
}
