/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.melexis.product;

import com.melexis.*;
import com.melexis.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author brh
 */
@Controller
@RequestMapping("/product/add.do")
@SessionAttributes("product")
public class AddProductFormController {

	private final ProductRepository productRepository;

	@Autowired
	public AddProductFormController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}



	@RequestMapping(method=RequestMethod.GET)
	public String setupForm(ModelMap map) {
		map.addAttribute("product", new Product());
		return "product/form";
	}


	@RequestMapping(method=RequestMethod.POST)
	public String processSubmit(@ModelAttribute("product") Product p) {
		productRepository.addProduct(p);

		return "redirect:/product/list.do";
	}
}
