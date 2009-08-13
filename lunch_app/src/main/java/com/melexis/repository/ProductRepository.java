/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.melexis.repository;

import com.melexis.Product;
import java.util.List;

/**
 *
 * @author brh
 */
public interface ProductRepository {

	/**
	 * List all the available products
	 * @return a list of all the products
	 */
	List<Product> findAvailableProducts();

	/**
	 * Add a product to the repository
	 * @param product the product
	 * @return the saved product
	 */
	Product addProduct(Product product);

	/**
	 * Update a product in the repository
	 * @param product the product
	 * @return the updated product
	 */
	Product updateProduct(Product product);

	/**
	 * Delete a product from the repository
	 * @param product the product
	 */
	void deleteProduct(Product product);

}
