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

import com.melexis.repository.DepositRepository;
import com.melexis.repository.UserProfileRepositoryBean;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author brh
 */
public class DepositPage extends WebPage {

	@SpringBean
	private UserProfileRepositoryBean userProfileRepositoryBean;

	@SpringBean
	private DepositRepository depositRepository;

	public DepositPage() {

	}

//	public class DepositForm extends Form<Deposit> {
//
//		public DepositForm() {
//			add(new TextField("name", null));
//		}
//	}

//	public class DetachableDepositModel extends LoadableDetachableModel<DepositModel> {
//
//		private final int id;
//
//		public DetachableDepositModel(int id) {
//			this.id = id;
//		}
//
//		@Override
//		protected DepositModel load() {
//			depo
//		}
//
//	}

	public class DepositModel {

		private final String who;
		private final String user;
		private final Double amount;

		public DepositModel(String who, String user, Double amount) {
			this.who = who;
			this.user = user;
			this.amount = amount;
		}

	}

}
