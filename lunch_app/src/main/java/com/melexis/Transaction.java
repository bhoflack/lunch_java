package com.melexis;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author brh
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="class",discriminatorType=DiscriminatorType.STRING)
public abstract class Transaction implements Serializable {

	@Id @GeneratedValue
	private Integer id;

	@ManyToOne
	private UserProfile who;
	@ManyToOne
	private UserProfile user;
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date transaction_date;
	@ManyToMany(targetEntity=Product.class)
	private Collection<Product> products;
	private Double amount;

	public Transaction() {

	}

	public Transaction(UserProfile who, UserProfile user, Date date,
		Collection<Product> products, Double amount) {
		this();
		this.who = who;
		this.user = user;
		this.transaction_date = date;
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
		return transaction_date;
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
		return String.format("%s performed transaction at %s with amount %s to user %s",getWho(), getDate(), getAmount(), getUser());
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
