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

import com.melexis.repository.OrderRepository;
import com.melexis.repository.ProductRepository;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.hibernate.criterion.DetachedCriteria;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 *
 * @author kry
 */
public class OrderProduct extends WebPage {

    @SpringBean
    private ProductRepository productRepository;

    public OrderProduct() {

        add(new OrderForm("orderForm"));

        /*
        add(new Link("getOrder") {

        @Override
        public void onClick() {

        int i = 0;

        for (i = 0; i < RefreshingView.class.getFields().length; i++) {
        Field f;
        //f = RefreshingView.class.getField("table:" + i + ":quantity");
        //sField += f.toString();
        sField = RefreshingView.class.getFields().toString();
        sField += "length = " + RefreshingView.class.getFields().length;
        }

        try {
        sField += "   field table = " + RefreshingView.class.getField("table:4:quantity");
        } catch (NoSuchFieldException ex) {
        Logger.getLogger(OrderProduct.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
        Logger.getLogger(OrderProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(sField);
        }
        });
         */
    }

    private class OrderForm extends Form {

        private final RefreshingView<DetachableModelOrder> refreshingView;

        public OrderForm(String id) {
            super(id);

            refreshingView = new RefreshingView<DetachableModelOrder>("table") {

                @Override
                protected Iterator<IModel<DetachableModelOrder>> getItemModels() {
                    List<Product> products = productRepository.findAvailableProducts();
                    List<IModel<DetachableModelOrder>> models = new ArrayList<IModel<DetachableModelOrder>>();

                    for (Product p : products) {
                        DetachableModelOrder model = new DetachableModelOrder(productRepository, p.getId());
                        Model<DetachableModelOrder> m = new Model<DetachableModelOrder>(model);

                        models.add(m);
                    }

                    return models.iterator();
                }

                @Override
                protected void populateItem(Item<DetachableModelOrder> item) {
                    DetachableModelOrder m = item.getModelObject();
                    ModelOrder model = m.getObject();

                    item.add(new TextField("quantity", new PropertyModel(model, "quantity")));
                    item.add(new Label("name", model.getName()));
                    item.add(new Label("price", model.getPrice().toString()));
                }
            };

            add(refreshingView);
        }

        @Override
        public void onSubmit() {
            int quantity = 0;
            Iterator<Item<DetachableModelOrder>> iter = refreshingView.getItems();

            while (iter.hasNext()) {
                DetachableModelOrder m = iter.next().getModelObject();
                //quantity = Integer.parseInt(((TextField) refreshingView.getItems().next().get("quantity")).getInput());
                quantity += m.getObject().getQuantity();
                //m.getObject().setQuantity(quantity);
                //System.out.println(((TextField) iter.next().get(0)).getInput());
                System.out.println("quantity " + m.getObject().getName() + " : " + quantity);
            }

            System.out.println("quantity : " + quantity);
        }
    }
}
