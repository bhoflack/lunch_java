/*
 *  Copyright 2009 brh.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */

package com.melexis;

import com.melexis.repository.UserProfileRepository;
import org.apache.wicket.model.LoadableDetachableModel;

/**
 *
 * @author brh
 */
public class DetachableUserProfileModel extends LoadableDetachableModel<UserProfile> {

	private final String name;
	private final UserProfileRepository userProfileRepository;

	public DetachableUserProfileModel(UserProfileRepository userProfileRepository,
		String name) {
		this.name = name;
		this.userProfileRepository = userProfileRepository;
	}

	@Override
	protected UserProfile load() {
		return userProfileRepository.findUserOrCreateNew(name);
	}

}
