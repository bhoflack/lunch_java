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

import com.melexis.repository.ProductRepository;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author brh
 */
public class AddProductPage extends WebPage {

	@SpringBean
	ProductRepository productRepository;

	public AddProductPage() {
		add(new AddProductForm("productForm"));
	}

	public final class AddProductForm extends Form {
		private final Product product;

		public AddProductForm(final String componentName) {
			super(componentName);

			product = new Product();
			add(new Label("nameLabel", "name"));
			add(new TextField("name", new PropertyModel(product, "name")));
			add(new Label("priceLabel", "label"));
			add(new TextField("price", new PropertyModel(product, "price")));
		}

		public final void onSubmit() {
			productRepository.addProduct(product);
		}

	}

}
