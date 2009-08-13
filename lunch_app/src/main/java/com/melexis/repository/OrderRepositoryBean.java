/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.melexis.repository;

import com.melexis.InsufficientBalanceException;
import com.melexis.Order;
import com.melexis.Transaction;
import com.melexis.UserProfile;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 *
 * @author brh
 */
public class OrderRepositoryBean implements OrderRepository {

	private HibernateTemplate hibernateTemplate;

	public OrderRepositoryBean(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
		hibernateTemplate.setFlushMode(hibernateTemplate.FLUSH_ALWAYS);
	}

	public List<Order> ordersForToday() {
		Calendar c = Calendar.getInstance();
		GregorianCalendar today = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE));
		Calendar tomorrow = Calendar.getInstance();
		c.add(Calendar.DATE, 1);
		return hibernateTemplate.findByNamedQueryAndNamedParam("order.findBetweenDateAndMaxdate",
			new String[] {"date", "maxdate"}, new Date[] {today.getTime(), tomorrow.getTime()});
	}

	public Order order(Order t) throws InsufficientBalanceException {
		hibernateTemplate.save(t);
		UserProfile u = t.getUser();
		u.setBalance(u.getBalance() - t.getAmount());

		if (u.getBalance() < 0 && !t.getWho().isAdmin()) {
			throw new InsufficientBalanceException();
		}
		hibernateTemplate.merge(u);
		hibernateTemplate.flush();
		return t;
	}
}
