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
	public void setUp() {
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

}
