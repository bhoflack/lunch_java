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
import java.util.Iterator;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;

/**
 *
 * @author brh
 */
public class SortableUserProfileDataProvider extends SortableDataProvider<UserProfile> {

	private final UserProfileRepository userProfileRepository;

	public SortableUserProfileDataProvider(UserProfileRepository userProfileRepository) {
		this.userProfileRepository = userProfileRepository;
	}

	public Iterator<? extends UserProfile> iterator(int offset, int length) {
		return userProfileRepository.findAll().subList(offset,
			offset + length).iterator();
	}

	public int size() {
		return userProfileRepository.findAll().size();
	}

	public IModel<UserProfile> model(UserProfile userProfile) {
		return new DetachableUserProfileModel(userProfileRepository,
			userProfile.getName());
	}

}
