/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.melexis.integration;

import com.melexis.InsufficientBalanceException;
import com.melexis.InsufficientPriviledgesException;
import com.melexis.Order;
import com.melexis.Product;
import com.melexis.UserProfile;
import com.melexis.repository.OrderRepository;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.*;

/**
 *
 * @author brh
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/beans.xml", "classpath:/test-datasource.xml"})
public class OrderRepositoryTest {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private SessionFactory sessionFactory;

	private HibernateTemplate hibernateTemplate;

	private UserProfile brh;
	private UserProfile userWithMoney;
	private UserProfile userWithoutMoney;
	private UserProfile admin;

	private Product martino;
	private Product hamkaas;
	private Product slaatje;

	@Before
	public void setUp() throws InsufficientPriviledgesException {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
		
		brh = new UserProfile("brh", 15.);
		userWithMoney = new UserProfile("userWithMoney", 12.);
		userWithoutMoney = new UserProfile("userWithoutMoney", 0.);
		admin = new UserProfile("admin", 0., Boolean.TRUE);

		hibernateTemplate.save(brh);
		hibernateTemplate.save(userWithMoney);
		hibernateTemplate.save(userWithoutMoney);
		hibernateTemplate.save(admin);

		martino = new Product("martino", 3.1);
		hamkaas = new Product("ham kaas", 2.8);
		slaatje = new Product("slaatje", 3.5);

		hibernateTemplate.save(martino);
		hibernateTemplate.save(hamkaas);
		hibernateTemplate.save(slaatje);

		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -1);
		Date yesterday = new Date(c.getTimeInMillis());

		Calendar c2 = Calendar.getInstance();
		c2.add(Calendar.HOUR, -1);
		Date todayOneHourAgo = new Date(c2.getTimeInMillis());
		
		Order[] orders = new Order[] {
			new Order(brh, brh, new Date(), Arrays.asList(martino)),
			new Order(userWithMoney, userWithMoney, new Date(), Arrays.asList(martino, hamkaas)),
			new Order(userWithMoney, userWithMoney, yesterday, Arrays.asList(martino, hamkaas)),
			new Order(brh, brh, todayOneHourAgo, Arrays.asList(hamkaas))
		};



		for (Order o : orders) {
			hibernateTemplate.save(o);
		}
	}

	@Test
	public void testOrdersToday() {
		List<Order> orders = orderRepository.findOrdersForToday();

		assertEquals("The order list should return only the 3 orders for today",
			3, orders.size());
	}

	@Test
	@Transactional
	public void testOrder() throws InsufficientBalanceException, InsufficientPriviledgesException {
		Order o = new Order(brh, brh, new Date(), Arrays.asList(slaatje));
		orderRepository.executeOrder(o);
		doesTheDbContainTheNewOrder(o);
		verifyUserBalance(brh, 11.5);
	}

	@Test(expected=InsufficientBalanceException.class)
	public void testInvalidOrder() throws InsufficientBalanceException, InsufficientPriviledgesException {
		Order o  = new Order(userWithoutMoney, userWithoutMoney, new Date(), Arrays.asList(martino));
		orderRepository.executeOrder(o);
	}

	@Test
	@Transactional
	public void testOrderBalanceBelowZeroAdmin() throws InsufficientBalanceException, InsufficientPriviledgesException {
		Order o  = new Order(admin, userWithoutMoney, new Date(), Arrays.asList(martino));
		orderRepository.executeOrder(o);

		doesTheDbContainTheNewOrder(o);
		verifyUserBalance(userWithoutMoney, -3.1);
	}

	@Test
	public void testOrderOverviewTodayForBrh() {
		List<Order> orders = orderRepository.findOrdersForTodayForUser("brh");

		assertEquals("The order list for today should return 2 orders for brh.", 2, orders.size());
	}

	@Test
	public void testOrderOverviewTodayForUserWithMoney() throws InsufficientPriviledgesException {
		List<Order> orders = orderRepository.findOrdersForTodayForUser(userWithMoney.getName());

		assertEquals("The overview should only contain the order for today.", 1, orders.size());
		assertTrue("The overview should contain the correct order.",
			orders.contains(new Order(userWithMoney, userWithMoney, new Date(), Arrays.asList(martino, hamkaas))));
	}

	@Test
	public void testOrderOverviewTodayForUnknownUser() {
		List<Order> orders = orderRepository.findOrdersForTodayForUser("unknown_user");

		assertEquals("The unknown user should not have any orders.", 0, orders.size());
	}

	private void doesTheDbContainTheNewOrder(Order o) throws DataAccessException {
		// now verify if the order was added to the repository
		List<Order> orders = hibernateTemplate.find("from Order");
		assertTrue("The orderlist should contain the new order.", orders.contains(o));
	}

	private void verifyUserBalance(UserProfile user, Double balance) throws DataAccessException {
		// verify if the price was subtracted from the user balance
		UserProfile user_db = (UserProfile) hibernateTemplate.find("from UserProfile where id=?", user.getId()).get(0);
		assertEquals("The price of the product should be subtracted from the user balance", balance,
			user_db.getBalance().doubleValue(), 0.001);
	}

}
