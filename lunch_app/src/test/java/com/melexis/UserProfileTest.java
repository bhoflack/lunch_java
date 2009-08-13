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
public class UserProfileTest {

	private UserProfile brh;
	private UserProfile another;

	@Before
	public void setUp() {
		brh = new UserProfile("brh", 15.);
		another = new UserProfile("an other", 12.);
	}

	@Test
	public void testEquals() {
		assertEquals("The same object should equal", brh, brh);
	}

	@Test
	public void testAnotherObjectShouldNotBeEqual() {
		assertNotSame("Other objects should differ", another, brh);
	}

}
