package com.mainclass.entities;

import javax.persistence.*;

@Entity
@Table(name="orders")
@Access(AccessType.FIELD)
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	private long price;
	private boolean active;
	
	public Order() {
		super();
	}	

	public Order(long id, String name, long price, boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.active = active;
	}
	
	public Order(long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
}
