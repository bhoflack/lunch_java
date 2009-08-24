package com.melexis.repository;

import com.melexis.UserProfile;
import java.util.List;

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


	/**
	 * Find all UserProfiles.
	 * @return a list of UserProfiles.
	 */
	List<UserProfile> findAll();

	/**
	 * Update the userProfile to the repository.
	 * @param u the userProfile
	 */
	void update(UserProfile u);

}
