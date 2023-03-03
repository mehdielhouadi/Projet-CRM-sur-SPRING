package com.example.validation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.validation.entity.Order;
import com.example.validation.repository.OrderRepository;
@Service
public class OrderService {

	@Autowired
	OrderRepository oRepo;
	
	public Order getOne(int id) {
		return oRepo.findById(id).orElse(null);
	}
	
	public List<Order> getAll() {
		return oRepo.findAll();
	}
	
	public void create(Order o) {
		oRepo.save(o);
	}
	
	public void update(int id, Order o) {
		Order order = getOne(id);
		if(order!=null) {
			order.setDesignation(o.getDesignation());
			order.setNbDays(o.getNbDays());
			order.setState(o.getState());
			order.setTypePrestation(o.getTypePrestation());
			order.setUnitPrice(o.getUnitPrice());
			order.setCustomer(o.getCustomer());
			oRepo.save(order);
		}
	}
	
	public void delete(Order o) {
		oRepo.delete(o);
	}
	
	
	//1
	public Page<Order> findAllOrdersOfCustumer(int page, int nombre , int id) {
		Pageable p = PageRequest.of(page, nombre);
		return oRepo.findAllOrdersOfCustomer(p, id); 
	}
	
	
	//2
	public Page<Order> findAllInPages (int page , int nombre) {
		Pageable p = PageRequest.of(page, nombre);
		return oRepo.findAll(p);
	}
	
	//4
	public List<Order> findOrdersInCountry(String country) {
		return oRepo.findAllByCountry(country);
	}
	
	//5
	public List<Order> getOrdersWithPriceGreater(float price){
		return oRepo.findByPriceGreateThan(price);
	}
	
	//7
	public int numberOfOrdersOfCustomer(int idCustomer) {
		return oRepo.numberOfOrdersOfCustomer(idCustomer);
	}
	
	//8
	public int sumOfOrdersOfCustomer(int idCustomer) {
		return oRepo.sumOfOrdersOfCustomer(idCustomer);
	}
	
	public List<Order> findByIdCustomer(int id) {
		return oRepo.findAllByIdCustomer(id);
	}
}
