/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.melexis;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.NamedQuery;


/**
 *
 * @author brh
 */

@NamedQuery(name="userProfile.findByName", query="from UserProfile u where u.name = :name")

@Entity
public class UserProfile implements Serializable {

	@Id @GeneratedValue
	private Integer id;
	private String name;
	private Double balance;

	public UserProfile() {

	}

	public UserProfile(String name, Double balance) {
		this();
		this.name = name;
		this.balance = balance;
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
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the balance
	 */
	public Double getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof UserProfile)) {
			return false;
		}

		UserProfile o = (UserProfile) other;
		
		return (name != null)? o.name == null : name.equals(o.name) &&
			(balance != null)? o.balance == null : balance.equals(o.balance);
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 41 * hash + (this.name != null ? this.name.hashCode() : 0);
		hash = 41 * hash + (this.balance != null ? this.balance.hashCode() : 0);
		return hash;
	}

}
