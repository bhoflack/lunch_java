package com.melexis;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author brh
 */
@Entity
public class Product implements Serializable {

	@Id @GeneratedValue
	private Integer id;
	private String name;
	private Double price;

	public Product() {

	}

	public Product(String name, double price) {
		this();
		this.name = name;
		this.price = price;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}
	
	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Product)) {
			return false;
		}

		Product o = (Product) other;

		return (name == null)? o.name == null : name.equals(o.name) &&
			(price == null)? o.price == null : price.equals(o.price);
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 47 * hash + (this.name != null ? this.name.hashCode() : 0);
		hash = 47 * hash + (this.price != null ? this.price.hashCode() : 0);
		return hash;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}


}
