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
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;

/**
 *
 * @author brh
 */
public class ProductList extends WebPage {

	@SpringBean
	private ProductRepository productRepository;
	private final AjaxFallbackDefaultDataTable<Product> ajaxFallbackDefaultDataTable;

	public ProductList() {

		List<IColumn<Product>> columns = new ArrayList<IColumn<Product>>();


//		columns.add(new AbstractColumn<Product>(new Model<Product>("Actions")) {
//
//			public void populateItem(Item<ICellPopulator<Product>> cellItem, String componentId,
//				IModel<Product> model)
//			{
//				cellItem.add(new ActionPanel(componentId, model));
//			}
//		});
		columns.add(new PropertyColumn(new Model<String>("ID"), "id"));
		columns.add(new PropertyColumn(new Model<String>("Name"), "name"));
		columns.add(new PropertyColumn(new Model<String>("Price"), "price"));

		ajaxFallbackDefaultDataTable = new AjaxFallbackDefaultDataTable<Product>("table", columns,
			new SortableProductDataProvider(productRepository), 20);

		add(ajaxFallbackDefaultDataTable);
		add(new AddProductForm("productForm"));
	}

	public final class ProductListModel extends LoadableDetachableModel {

		@Override
		protected Object load() {
			return productRepository.findAvailableProducts();
		}

	}

	public final class AddProductForm extends Form {
		private final Product product;

		public AddProductForm(final String componentName) {
			super(componentName);

			product = new Product();
			add(new Label("nameLabel", "name"));
			add(new TextField("name", new PropertyModel(product, "name")));
			add(new Label("priceLabel", "price"));
			add(new TextField("price", new PropertyModel(product, "price")));
		}

		@Override
		public final void onSubmit() {
			productRepository.addProduct(product);
			ajaxFallbackDefaultDataTable.modelChanged();
		}
	}
}
