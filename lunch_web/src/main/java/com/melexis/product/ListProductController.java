/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.melexis.product;

import com.melexis.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author brh
 */
@Controller
@SessionAttributes("product")
public class ListProductController {

	private final ProductRepository productRepository;
	

	@Autowired
	public ListProductController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@RequestMapping("/product/list.do")
	public ModelMap list() {
		return new ModelMap("products", productRepository.findAvailableProducts());
	}

}
