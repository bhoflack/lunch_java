/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.melexis;

import com.melexis.repository.ProductRepository;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author brh
 */
@Controller
public class ProductController {

//	private final ProductRepository productRepository;
//
//	@Autowired
//	public ProductController(ProductRepository productRepository) {
//		this.productRepository = productRepository;
//	}

	@RequestMapping("/product/list.do")
	public ModelMap list() {
		//return productRepository.findAvailableProducts();
		return new ModelMap("products", Arrays.asList(new Product[] {
			new Product("martino", 1.23),
			new Product("ham kaas", 4.56)
		}));
	}

}
