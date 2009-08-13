/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.melexis;

import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author brh
 */
@Entity
@DiscriminatorValue("DEPOSIT")
public class Deposit extends Transaction {

	public Deposit() {
		super();
	}

	public Deposit(UserProfile who, UserProfile user, Date date, Double amount) throws InsufficientPriviledgesException {
		super(who, user, date, null, amount);

		if (!who.isAdmin()) {
			throw new InsufficientPriviledgesException();
		}
	}

	@Override
	public String toString() {
		return String.format("%s deposited %s to user %s at %s", getWho(),
			getAmount(), getUser(), getDate());
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Deposit)) {
			return false;
		}

		Deposit o = (Deposit) other;

		return (getWho() != null)? getWho().equals(o.getWho()) : o.getWho() == null &&
			(getUser() != null)? getUser().equals(o.getUser()) : o.getUser() == null &&
			(getDate() != null)? getDate().equals(o.getDate()) : o.getDate() == null &&
			(getAmount() != null)? getAmount().equals(o.getAmount()) : o.getAmount() == null;
	}
}
