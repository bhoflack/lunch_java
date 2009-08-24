/*
 *  Copyright 2009 kry.
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
import com.melexis.repository.OrderRepository;
import com.melexis.repository.ProductRepository;
import com.melexis.repository.UserProfileRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 *
 * @author kry
 */
public class OrderProduct extends WebPage {

    @SpringBean
    private ProductRepository productRepository;

    @SpringBean
    private OrderRepository orderRepository;

    @SpringBean
    private UserProfileRepository userProfileRepository;

    @SpringBean
    private DepositRepository depositRepository;

    public OrderProduct() {

        RefreshingView<DetachableModelOrder> refreshingView = new RefreshingView<DetachableModelOrder>("table") {

            @Override
            protected Iterator<IModel<DetachableModelOrder>> getItemModels() {
                List<Product> products = productRepository.findAvailableProducts();
                List<IModel<DetachableModelOrder>> models = new ArrayList<IModel<DetachableModelOrder>>();
                
                for (Product p:products)
                {
                    DetachableModelOrder model = new DetachableModelOrder(productRepository, p.getId());
                    Model<DetachableModelOrder> m = new Model<DetachableModelOrder>(model);

                    models.add(m);
                }

                return models.iterator();
            }

            @Override
            protected void populateItem(final Item<DetachableModelOrder> item) {
                DetachableModelOrder m = item.getModelObject();
                ModelOrder model = m.getObject();

                item.add(new TextField("quantity", new PropertyModel(model, "quantity")));
                item.add(new Label("name", model.getName()));
                item.add(new Label("price", model.getPrice().toString()));
		item.add(new Link("order") {

					@Override
					public void onClick() {
						DetachableModelOrder m = item.getModelObject();
						UserProfile brh = userProfileRepository.findUserOrCreateNew("brh");
						try {
							depositRepository.executeDeposit(new Deposit(brh, brh, new Date(), 15.));
							orderRepository.executeOrder(m.getObject().createOrder(brh, brh));
						} catch (Exception ex) {
							Logger.getLogger(OrderProduct.class.getName()).log(Level.SEVERE, null, ex);
						}
					}
				});
            }
        };

        add(refreshingView);
    }
}
