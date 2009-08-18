/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.melexis.repository;

import com.melexis.Product;
import com.melexis.ProductNotFoundException;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 *
 * @author brh
 */
public class ProductRepositoryBean implements ProductRepository {

	private final HibernateTemplate hibernateTemplate;
	
	public ProductRepositoryBean(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	public List<Product> findAvailableProducts() {
		return hibernateTemplate.find("from Product");
	}

	public Product addProduct(Product product) {
		hibernateTemplate.save(product);
		return product;
	}

	public Product updateProduct(Product product) {
		hibernateTemplate.update(product);
		return product;
	}

	public void deleteProduct(Product product) {
		hibernateTemplate.bulkUpdate("delete from Product p where p.id = ?", product.getId());
		hibernateTemplate.flush();
	}

	public Product findById(Integer id) throws ProductNotFoundException {
		List<Product> results = hibernateTemplate
			.find("from Product p where p.id = ?", id);
		if (results.size() > 0) {
			return results.get(0);
		}
		throw new ProductNotFoundException();
	}

}
