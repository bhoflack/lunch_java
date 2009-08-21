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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.IPageLink;
import org.apache.wicket.markup.html.link.PageLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

/**
 *
 * @author brh
 */
public class EditProduct extends WebPage {
	
	private final Product product;
	private final ProductRepository productRepository;

	public EditProduct(Integer id, ProductRepository productRepository) throws ProductNotFoundException {
		this.productRepository = productRepository;

		product = productRepository.findById(id);
		add(new EditProductForm("productForm"));
	}

	public static PageLink link(final String name, final IModel<Product> model, final ProductRepository productRepository) {
		return new PageLink(name, new IPageLink() {

			public Page getPage() {
				try {
					Product p = model.getObject();
					return new EditProduct(p.getId(), productRepository);
				} catch (ProductNotFoundException ex) {
					Logger.getLogger(EditProduct.class.getName()).log(Level.SEVERE, null, ex);
					return new Error();
				}
			}

			public Class<? extends Page> getPageIdentity() {
				return EditProduct.class;
			}
		});
	}

	final class EditProductForm extends Form {

		public EditProductForm(String id) {
			super(id);

			add(new Label("nameLabel", "name"));
			add(new TextField("name", new PropertyModel(product, "name")));
			add(new Label("priceLabel", "price"));
			add(new TextField("price", new PropertyModel(product, "price")));
		}

		@Override
		public void onSubmit() {
			productRepository.updateProduct(product);
		}
	}


}
