/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.melexis;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author brh
 */
public class ProductTest {

	private Product martino;
	private Product slaatje;

	@Before
	public void setUp() {
		martino = new Product("martino", 3.2);
		slaatje = new Product("slaatje", 2.5);
	}

	@Test
	public void testEquals() {
		assertEquals("The same products should equal.", martino, martino);
	}

	@Test
	public void testDiffer() {
		assertNotSame("Other products should differ.", martino, slaatje);
	}

}
