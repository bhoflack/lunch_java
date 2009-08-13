/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.melexis;

import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author brh
 */
@NamedQueries(
	@NamedQuery(name="order.findBetweenDateAndMaxdate", query="from Order o where transaction_date between :date and :maxdate")
)

@Entity
@DiscriminatorValue("ORDER")
public class Order extends Transaction {

	public Order() {}

	public Order(UserProfile who, UserProfile user, Date date, 
		Collection<Product> products) {
		super(who, user, date, products, totalAmount(products));
	}

	private static Double totalAmount(Collection<Product> products) {
		Double sum = new Double(0.);

		for (Product p : products) {
			sum += p.getPrice();
		}

		return sum;
	}

	@Override
	public String toString() {
		return String.format("%s ordered %s for account %s at %s", getWho(), getProducts(), getUser(), getDate());
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Order)) {
			return false;
		}

		Order o = (Order) other;


		return (getWho() != null)? getWho().equals(o.getWho()) : o.getWho() == null &&
			(getUser() != null)? getUser().equals(o.getUser()) : o.getUser() == null &&
			(getDate() != null)? getDate().equals(o.getDate()) : o.getDate() == null &&
			(getProducts() != null)? getProducts().equals(o.getProducts()) : o.getProducts() == null &&
			(getAmount() != null)? getAmount().equals(o.getAmount()) : o.getAmount() == null;
	}

}
