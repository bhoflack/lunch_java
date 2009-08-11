/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.melexis.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 *
 * @author brh
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"beans.xml"})
public class UserProfileRepositoryTest {

	@Test
	public void fooTest() {
		assertTrue(true);
	}

}
