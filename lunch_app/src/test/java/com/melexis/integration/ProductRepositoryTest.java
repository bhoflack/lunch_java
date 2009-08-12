/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.melexis.integration;
import com.melexis.Product;
import com.melexis.repository.ProductRepository;
import java.util.List;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 *
 * @author brh
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/beans.xml", "classpath:/test-datasource.xml"})
public class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private SessionFactory sessionFactory;
	private HibernateTemplate t;

	private Product sandwich;
	private Product martino;
	private Product club;

	@Before
	public void setUp() {
		t = new HibernateTemplate(sessionFactory);
		
		sandwich = new Product("sandwich", 1.33);
		martino = new Product("martino", 3.4);
		club = new Product("club", 3.);

		t.save(sandwich);
		t.save(martino);
		t.save(club);
	}

	@After
	public void after() {
		System.out.println("Deleting product ...");
		t.bulkUpdate("delete from Product");
	}

	@Test
	public void testAddNewProduct() {
		Product p = new Product("new product", 1.00);

		assertEquals("No products with this name should be available in the db.",
			0, t.find("from Product p where p.name = ?", p.getName()).size());
		productRepository.add(p);
		assertEquals("One product with this name should be available in the db.",
			1, t.find("from Product p where p.name = ?", p.getName()).size());

		assertTrue("The id should be filled in for the product.", p.getId() != null);
	}

	@Test
	public void testUpdateProduct() {
		Product p = (Product) t.find("from Product p where p.name = 'sandwich'").get(0);
		p.setPrice(1.98);
		p = productRepository.update(p);
		Product sandwich = (Product) t.find("from Product p where p.name = 'sandwich'").get(0);
		assertEquals("The update function should update the product in the database.",
			1.98, sandwich.getPrice().doubleValue(), 0.001);
	}

	@Test
	public void testDelete() {
		List<Product> products_before = t.find("from Product");
		assertTrue("The repository should contain the product before deleting",
			products_before.contains(sandwich));
		productRepository.delete(sandwich);
		t.flush();
		List<Product> products = t.find("from Product");

		assertFalse("The repository should not contain this product anymore after deleting.",
			products.contains(sandwich));
	}

	@Test
	public void testListAvailable() {
		List<Product> products = productRepository.listAvailable();
		assertEquals("The list should return all the products.",
			3, products.size());
	}

}
