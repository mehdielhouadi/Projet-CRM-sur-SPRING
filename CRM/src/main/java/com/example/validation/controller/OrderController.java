package com.example.validation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.validation.entity.Order;
import com.example.validation.service.CustomerService;
import com.example.validation.service.OrderService;
import com.github.javafaker.Faker;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderService oService;
	@Autowired
	CustomerService cService;
	
	@PostMapping("/fake/{nbOrders}/{idClient}")
	public List<Order> fakeOrders(@PathVariable("nbOrders") int nbOrders, @PathVariable("idClient") int idClient){
		List<Order> fakeOrders = new ArrayList<>();
		for ( int i = 0 ; i < nbOrders ; i++ ) {
			Order order = new Order(cService.getOne(idClient));
			oService.create(order);
			fakeOrders.add(order);
		}
		return fakeOrders;
	}
	@PostMapping("/fake")
	public List<Order> fakeOrderstoRandomCustomer(){
		List<Order> fakeOrders = new ArrayList<>();
		Faker f = new Faker();
		for (int j : cService.listOfIds()) {
			int nbOrders = f.random().nextInt(1, 10);
			for ( int i = 0 ; i < nbOrders ; i++ ) {
				Order order = new Order(cService.getOne(j));
				oService.create(order);
				fakeOrders.add(order);
			}
		}
		return fakeOrders;
	}
	
	@GetMapping
	public List<Order> findAll() {
		return oService.getAll();
	}
	
	@GetMapping("/{id}")
	public Order findById(@PathVariable("id") int id) {
		return oService.getOne(id);
	}
	
	@PostMapping("/{idCustomer}")
	public void saveOrder(@RequestBody Order o, @PathVariable("idCustomer") int id) {
		Order order = new Order(cService.getOne(id));
		oService.create(order);
	}
	
	@PutMapping("/{idOrder}")
	public void updateOrder(@RequestBody Order o, @PathVariable("idOrder") int id) {
		oService.update(id, o);
	}
	
	@DeleteMapping("/{idOrder}")
	public void deleteOrder (@PathVariable("idOrder") int id) {
		oService.delete(oService.getOne(id));
	}
	
	//1
	@GetMapping("/pages/{idCustomer}/{page}/{nombre}")
	public Page<Order> getAllOrdersOfCustomerInPages(@PathVariable("page") int page, @PathVariable("nombre") int nombre, @PathVariable("idCustomer") int idCustomer) {
		return oService.findAllOrdersOfCustumer(page, nombre,idCustomer);
	}
	
	
	//2
	@GetMapping("/pages/{page}/{nombre}")
	public Page<Order> getAllOrdersInPages(@PathVariable("page") int page, @PathVariable("nombre") int nombre){
		return oService.findAllInPages(page, nombre);
	}
	
	//4
	@GetMapping("/country/{country}")
	public List<Order> getAllOrdersInCountry(@PathVariable("country") String country) {
		return oService.findOrdersInCountry(country);
	}
	
	//5
	@GetMapping("/price/{price}")
	public List<Order> getOrdersWithPriceGreater(@PathVariable("price") float price) {
		return oService.getOrdersWithPriceGreater(price);
	}
	
	//7
	@GetMapping("/count/{idCustomer}")
	public int numberOfOrdersOfCustomer(@PathVariable("idCustomer") int idCustomer) {
		return oService.numberOfOrdersOfCustomer(idCustomer);
	}
	
	//8
	@GetMapping("/sum/{idCustomer}")
	public int sumOfOrdersOfCustomer(@PathVariable("idCustomer") int idCustomer) {
		return oService.sumOfOrdersOfCustomer(idCustomer);
	}
	
}
