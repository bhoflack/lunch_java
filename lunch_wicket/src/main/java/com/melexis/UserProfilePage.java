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
import com.melexis.repository.UserProfileRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.PageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author brh
 */
@AuthorizeInstantiation("ADMIN")
public class UserProfilePage extends WebPage {

	@SpringBean
	private UserProfileRepository userProfileRepository;
	@SpringBean
	private DepositRepository depositRepository;

	public UserProfilePage() {
		List<IColumn<UserProfile>> columns = new ArrayList<IColumn<UserProfile>>();

		columns.add(new PropertyColumn<UserProfile>(new Model<String>("ID"), "id"));
		columns.add(new PropertyColumn<UserProfile>(new Model<String>("Name"), "name"));
		columns.add(new PropertyColumn<UserProfile>(new Model<String>("Balance"), "balance"));
		columns.add(new PropertyColumn<UserProfile>(new Model<String>("Admin"), "admin"));
		columns.add(new AbstractColumn<UserProfile>(new Model<String>("Actions")) {

			public void populateItem(Item<ICellPopulator<UserProfile>> item,
				String id, IModel<UserProfile> model) {
				item.add(new ActionPanel(id, model));
			}
		});


		add(new AddUserProfileForm("addUserProfileForm"));
		add(new AjaxFallbackDefaultDataTable("userProfileTable", columns,
			new SortableUserProfileDataProvider(userProfileRepository), 25));
		add(new PageLink("logout", LogoutPage.class));
	}

	private final class AddUserProfileForm extends Form<UserProfile> {

		private String username;

		public AddUserProfileForm(String id) {
			super(id);

			add(new TextField("name", new PropertyModel(this, "username")));
		}

		@Override
		public void onSubmit() {
			userProfileRepository.findUserOrCreateNew(username);
		}
	}

	private final class ActionPanel extends Panel {

		private final IModel<UserProfile> model;

		public ActionPanel(String id, IModel<UserProfile> m) {
			super(id);
			this.model = m;

			add(new Link("toggleAdmin") {

				@Override
				public void onClick() {
					UserProfile u = model.getObject();
					u.setAdmin(!u.isAdmin());
					userProfileRepository.update(u);
				}
			});

			add(new DepositForm("depositForm", model));
		}
	}

	private final class DepositForm extends Form {

		private final IModel<UserProfile> model;
		private Double amount;

		public DepositForm(String id, IModel<UserProfile> model) {
			super(id);

			this.model = model;
			add(new TextField("amount", new PropertyModel(this, "amount")));
		}

		@Override
		public void onSubmit() {
			LunchAuthenticatedWebSession laws = (LunchAuthenticatedWebSession) LunchAuthenticatedWebSession.get();
			String who = laws.getUsername();
			UserProfile w = userProfileRepository.findUserOrCreateNew(who);
			UserProfile u = model.getObject();
			try {
				Deposit deposit = new Deposit(w, u, new Date(), amount);
				depositRepository.executeDeposit(deposit);
			} catch (InsufficientPriviledgesException ex) {
				Logger.getLogger(UserProfilePage.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
}
