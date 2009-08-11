package com.melexis;

import java.util.Collection;
import java.util.Date;

/**
 *
 * @author brh
 */
public class Transaction {

	private final UserProfile who;
	private final UserProfile user;
	private final Date date;
	private final Collection<Product> products;
	private final Double amount;

	public Transaction(UserProfile who, UserProfile user, Date date,
		Collection<Product> products, Double amount) {
		this.who = who;
		this.user = user;
		this.date = date;
		this.products = products;
		this.amount = amount;
	}

	/**
	 * @return the who
	 */
	public UserProfile getWho() {
		return who;
	}

	/**
	 * @return the user
	 */
	public UserProfile getUser() {
		return user;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @return the products
	 */
	public Collection<Product> getProducts() {
		return products;
	}

	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	@Override
	public String toString() {
		return String.format("%s performed transaction at %s with amount %d to user %s",getWho(), getDate(), getAmount(), getUser());
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Transaction)) {
			return false;
		}

		Transaction o = (Transaction) other;
		return (getWho() != null)? o.getWho().equals(getWho()) : o.getWho() == null &&
			(getUser() != null)? o.getUser().equals(getUser()) : o.getUser() == null &&
			(getDate() != null)? o.getDate().equals(getDate()) : o.getDate() == null &&
			(getProducts() != null)? o.getProducts().equals(getProducts()) : o.getProducts() == null &&
			(getAmount() != null)? o.getAmount().equals(getAmount()) : o.getAmount() == null;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 79 * hash + (this.getWho() != null ? this.getWho().hashCode() : 0);
		hash = 79 * hash + (this.getUser() != null ? this.getUser().hashCode() : 0);
		hash = 79 * hash + (this.getDate() != null ? this.getDate().hashCode() : 0);
		hash = 79 * hash + (this.getProducts() != null ? this.getProducts().hashCode() : 0);
		hash = 79 * hash + (this.getAmount() != null ? this.getAmount().hashCode() : 0);
		return hash;
	}
}
