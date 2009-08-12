/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.melexis.repository;

import com.melexis.UserProfile;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 *
 * @author brh
 */
public class UserProfileRepositoryBean implements UserProfileRepository {

	private final HibernateTemplate hibernateTemplate;

	public UserProfileRepositoryBean(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	public UserProfile findUserOrCreateNew(String username) {
		List users = hibernateTemplate.findByNamedQueryAndNamedParam("userProfile.findByName", "name", username);

		if (users.size() > 0) {
			if (users.get(0) instanceof UserProfile) {
				return (UserProfile) users.get(0);
			} else {
				throw new AssertionError("Invalid userprofile");
			}
		}
		
		UserProfile u = new UserProfile(username, 0.0);
		hibernateTemplate.merge(u);
		return u;
	}
}
