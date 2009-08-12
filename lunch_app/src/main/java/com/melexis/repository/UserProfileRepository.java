package com.melexis.repository;

import com.melexis.UserProfile;

/**
 *
 * @author brh
 */
public interface UserProfileRepository {

	/**
	 * Find a UserProfile with the given username.  Or
	 * create a new one if the UserProfile does not exist already.
	 * @param username the username
	 * @return a UserProfile
	 */
	UserProfile findUserOrCreateNew(String username);

}
