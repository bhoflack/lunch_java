package com.melexis;

/**
 *
 * @author brh
 */
public class Product {

	private final String name;
	private final Double price;

	public Product(String name, double price) {
		this.name = name;
		this.price = price;
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
			(price == null)? o.price == null : price == o.price;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 47 * hash + (this.name != null ? this.name.hashCode() : 0);
		hash = 47 * hash + (this.price != null ? this.price.hashCode() : 0);
		return hash;
	}


}
