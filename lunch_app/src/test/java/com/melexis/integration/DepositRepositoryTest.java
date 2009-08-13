/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.melexis.integration;

import com.melexis.Deposit;
import com.melexis.UserProfile;
import com.melexis.repository.DepositRepository;
import com.melexis.repository.UserProfileRepository;
import java.util.Date;
import java.util.List;
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
public class DepositRepositoryTest {

	@Autowired
	private DepositRepository depositRepository;

	@Autowired
	private SessionFactory sess;

	private HibernateTemplate t;

	private UserProfile brh;
	private UserProfile userWithMoney;


	@Before
	public void setUp() {
		t = new HibernateTemplate(sess);

		brh = new UserProfile("brh", 12.);
		userWithMoney = new UserProfile("userwithmoney", 100.);

		t.save(brh);
		t.save(userWithMoney);
	}

	@Test
	public void deposit() {
		Deposit deposit = new Deposit(brh, brh, new Date(), 10.);

		// make the deposit
		depositRepository.executeDeposit(deposit);

		// verify that the deposit has been created
		List<Deposit> deposits = t.find("from Deposit");
		assertTrue("The deposit lists should contain the new deposit.",
			deposits.contains(deposit));

		// verify that the saldo has been updated.
		UserProfile brh_fromdb = (UserProfile) t.find("from UserProfile u where u.id=?", brh.getId()).get(0);
		assertEquals("The balance should be updated after the deposit.",
			22., brh_fromdb.getBalance().doubleValue(), 0.001);
	}

}
