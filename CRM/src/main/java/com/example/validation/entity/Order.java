package com.example.validation.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import com.github.javafaker.Faker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name="orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Length(min=3,max=35,message="Must be between 3 and 35 characters")
	private String typePrestation;
	@Length(min=3,max=35,message="Must be between 3 and 35 characters")
	private String designation;
	@Min(value=1,message="Must be at least one")
	private int nbDays;
	@Positive(message="Must be positive")
	private float unitPrice;
	@Range(min=0,max=1,message="Must be 0 or 1")
	private int state;
	    
	@ManyToOne
	@NotNull
	@JoinColumn(name="id_customer")
	private Customer customer;
	
    public Order(Customer c) {
    	Faker f = new Faker();
    	this.typePrestation = f.lorem().characters(3, 35);
    	this.designation = f.lorem().characters(3, 35);
    	this.nbDays = f.number().numberBetween(2, 1000);
    	this.unitPrice = (float) f.number().randomDouble(2, 5, 1000);
    	this.state = f.number().numberBetween(0, 2);
    	this.customer = c;
    }       
}
