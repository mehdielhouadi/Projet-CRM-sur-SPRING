package com.example.validation.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import com.github.javafaker.Faker;

import lombok.AllArgsConstructor;
import lombok.Data;
@Entity
@Table
@Data
@AllArgsConstructor
public class Customer {
    
	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Length(min=3,max=35,message="Must be between 3 and 35 characters")
	private String companyName;
	@Length(min=3,max=35,message="Must be between 3 and 35 characters")
	private String firstName;
	@Length(min=3,max=35,message="Must be between 3 and 35 characters")
	private String lastName;
	@Email(message="Must be an email addresse")
	private String email;
	@Length(min=10,max=10,message="Must be 10 characters")
	private String phone;
	@Length(min=3,max=35,message="Must be between 3 and 35 characters")
	private String address;
	@Length(min=5,max=5,message="Must be 5 characters")
	private String zipCode;
	@Length(min=3,max=35,message="Must be between 3 and 35 characters")
	private String city;
	@Length(min=2,max=35,message="Must be between 3 and 35 characters")
	private String country;
	@Range(min=0,max=1,message="Must be 0 or 1")
	private int state;
    
    public Customer() {
    	Faker f = new Faker();
    	this.companyName = f.company().name();
    	this.firstName = f.name().firstName();
    	this.lastName= f.name().lastName();
    	this.email = f.internet().emailAddress();
    	this.phone = f.number().digits(10);
    	this.address = f.address().streetAddress();
    	this.zipCode = f.number().digits(5);
    	this.city = f.address().city();
    	this.country = f.address().country();
    	this.state = f.number().numberBetween(0, 2);
    }
    
}
