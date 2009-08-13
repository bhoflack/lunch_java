/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.melexis.repository;

import com.melexis.Deposit;
import com.melexis.UserProfile;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author brh
 */
@Transactional
public class DepositRepositoryBean implements DepositRepository {

	private final HibernateTemplate t;

	public DepositRepositoryBean(SessionFactory factory) {
		t = new HibernateTemplate(factory);
	}

	public Deposit deposit(Deposit deposit) {
		t.save(deposit);
		UserProfile u = deposit.getUser();
		u.setBalance(u.getBalance() + deposit.getAmount());
		t.merge(u);
		t.flush();
		return deposit;
	}

}
