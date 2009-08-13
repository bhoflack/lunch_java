/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.melexis;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import org.junit.Before;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author brh
 */
public class OrderTest {

	private Order yesterday;
	private Order today;

	private UserProfile brh;

	private Product slaatje;

	@Before
	public void setUp() throws InsufficientPriviledgesException {
		brh = new UserProfile("brh", 3.2);
		slaatje = new Product("slaatje", 3.1);

		Calendar y = Calendar.getInstance();
		y.add(Calendar.DATE, -1);
		yesterday = new Order(brh, brh, y.getTime(), Arrays.asList(slaatje));
		today = new Order(brh, brh, new Date(), Arrays.asList(slaatje));
	}

	@Test
	public void testEquals() {
		assertEquals("The same objects should be equal.", today, today);
	}

	@Test
	public void testDiffer() {
		assertNotSame("Different objects should differ.", today, yesterday);
	}

	@Test(expected=InsufficientPriviledgesException.class)
	public void testOnlyTheUserOrAdminCanOrderForAnAccount() throws InsufficientPriviledgesException {
		UserProfile another = new UserProfile("another", 0.);
		Order o = new Order(another, brh, new Date(), null);
	}

	@Test
	public void testAnAdminCanOrderForAnyAccount() throws InsufficientPriviledgesException {
		UserProfile admin = new UserProfile("admin", 0.0, Boolean.TRUE);
		Order o = new Order(admin, brh, new Date(), null);

		assertTrue("A admin should be able to order for any account.", true);
	}

}
