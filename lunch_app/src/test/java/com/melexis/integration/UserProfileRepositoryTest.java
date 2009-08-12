/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.melexis.integration;

import com.melexis.UserProfile;
import com.melexis.repository.UserProfileRepository;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 *
 * @author brh
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/beans.xml", "classpath:/test-datasource.xml"})
public class UserProfileRepositoryTest {

	@Autowired
	private UserProfileRepository userProfileRepository;
	
	@Autowired
	private SessionFactory sessionFactory;

	@Before
	public void setUp() {
		HibernateTemplate template = new HibernateTemplate(sessionFactory);
		template.save(new UserProfile("brh", 24.0));
		template.save(new UserProfile("other", 12.1));
	}

	@Test
	public void fooTest() {
		assertTrue(true);

	}

	@Test
	public void testCreateANewUserProfile() {
		UserProfile u = userProfileRepository.findUserOrCreateNew("NEW_USER");
		assertNotNull("A new user profile should be created when it doest not exist already.",
			u);

		assertEquals("The username should be the one entered.", "NEW_USER", u.getName());
		assertTrue("The initial balance should be zero.", 0.0 == u.getBalance().doubleValue());
	}

	@Test
	public void testLoadExistingProfile() {
		UserProfile u = userProfileRepository.findUserOrCreateNew("brh");

		assertEquals("The username should be the one entered.", "brh", u.getName());
		assertEquals("The balance should be the one from the database.", 24.0 ,u.getBalance().doubleValue(), 0.00001);
	}

}
