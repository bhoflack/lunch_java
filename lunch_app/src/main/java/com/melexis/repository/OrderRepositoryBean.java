/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.melexis.repository;

import com.melexis.InsufficientBalanceException;
import com.melexis.Order;
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

	private final HibernateTemplate hibernateTemplate;
	private final UserProfileRepository userProfileRepository;

	public OrderRepositoryBean(SessionFactory sessionFactory,
		UserProfileRepository userProfileRepository) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
		this.userProfileRepository = userProfileRepository;
	}

	public List<Order> findOrdersForToday() {
		Calendar c = Calendar.getInstance();
		GregorianCalendar today = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE));
		Calendar tomorrow = Calendar.getInstance();
		c.add(Calendar.DATE, 1);
		return hibernateTemplate.findByNamedQueryAndNamedParam("order.findBetweenDateAndMaxdate",
			new String[] {"date", "maxdate"}, new Date[] {today.getTime(), tomorrow.getTime()});
	}

	public Order executeOrder(Order t) throws InsufficientBalanceException {
		t.getUser().payAmount(t.getAmount(), t.getWho());
		hibernateTemplate.save(t);
		hibernateTemplate.merge(t.getUser());
		hibernateTemplate.flush();
		return t;
	}

	public List<Order> findOrdersForTodayForUser(String username) {
		Calendar c = Calendar.getInstance();
		GregorianCalendar today = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE));
		Calendar tomorrow = Calendar.getInstance();
		c.add(Calendar.DATE, 1);
		UserProfile user = userProfileRepository.findUserOrCreateNew(username);
		return hibernateTemplate.findByNamedQueryAndNamedParam( "order.findWithUserAndBetweenDateAndMaxdate",
			new String[] {"date", "maxdate", "user"},
			new Object[] {today.getTime(), tomorrow.getTime(), user});
	}
}
