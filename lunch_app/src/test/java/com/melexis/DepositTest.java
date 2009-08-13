/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.melexis;

import java.util.Date;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author brh
 */
public class DepositTest {
	
	private UserProfile normalUser;
	private UserProfile adminUser;

	@Before
	public void setUp() {
		normalUser = new UserProfile("normalUser", 0.);
		adminUser = new UserProfile("admin", 0., Boolean.TRUE);
	}

	@Test(expected=InsufficientPriviledgesException.class)
	public void testDepositNormalUser() throws InsufficientPriviledgesException {
		Deposit d = new Deposit(normalUser, normalUser, new Date(), 100.);
	}

	@Test
	public void testDepositAdminUser() throws InsufficientPriviledgesException {
		Deposit d = new Deposit(adminUser, normalUser, new Date(), 100.);
	}

}
