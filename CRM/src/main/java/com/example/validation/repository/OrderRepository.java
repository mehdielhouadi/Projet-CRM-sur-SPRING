package com.example.validation.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.validation.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	//1
	@Query(value = "SELECT o FROM Order o JOIN o.customer c WHERE c.id = ?1")
	Page <Order> findAllOrdersOfCustomer(Pageable p,int id);
	
	//2
	Page <Order> findAll (Pageable p);
	
	//4
	@Query(value = "SELECT o FROM Order o JOIN o.customer c WHERE c.country = ?1")
	List<Order> findAllByCountry(String country);
	
	//5
	@Query(value = "SELECT o FROM Order o WHERE o.unitPrice > ?1")
	List<Order> findByPriceGreateThan(float price);
	
	
	//7
	@Query(value = "SELECT COUNT(o.id) FROM Order o JOIN o.customer c WHERE c.id=?1 ")
	Integer numberOfOrdersOfCustomer(int id);
	
	//8
	@Query(value = "SELECT SUM(o.unitPrice) FROM Order o JOIN o.customer c WHERE c.id=?1 ")
	Integer sumOfOrdersOfCustomer(int id);
	
	@Query(value = "SELECT o FROM Order o WHERE id_customer = ?1 ")
	List<Order> findAllByIdCustomer(int id);
	
} 
