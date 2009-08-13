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
	private Boolean admin;

	public UserProfile() {
		this.admin = Boolean.FALSE;
	}

	public UserProfile(String name, Double balance) {
		this();
		this.name = name;
		this.balance = balance;
	}

	public UserProfile(String name, Double balance, Boolean admin) {
		this(name, balance);
		this.admin = admin;
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

	/**
	 * @return the admin
	 */
	public Boolean isAdmin() {
		return admin;
	}

	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(Boolean admin) {
		this.admin = admin;
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
		
		return (name != null)? name.equals(o.name) : o.name == null &&
			(balance != null)? balance.equals(o.balance) : o.balance == null &&
			(isAdmin() != null)? isAdmin().equals(o.isAdmin()) : o.isAdmin() == null;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 41 * hash + (this.name != null ? this.name.hashCode() : 0);
		hash = 41 * hash + (this.balance != null ? this.balance.hashCode() : 0);
		hash = 41 * hash + (this.isAdmin() != null ? this.isAdmin().hashCode() : 0);
		return hash;
	}

}
