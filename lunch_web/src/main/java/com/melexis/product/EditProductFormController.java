/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.melexis.product;

import com.melexis.*;
import com.melexis.repository.ProductRepository;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author brh
 */
@Controller
@RequestMapping("/product/edit.do")
public class EditProductFormController {

	private final ProductRepository productRepository;

	@Autowired
	public EditProductFormController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@RequestMapping(method=RequestMethod.GET)
	public String setupForm(@RequestParam("productId") int productid, ModelMap map) {
		try {
			Product p = productRepository.findById(productid);
			map.addAttribute("product", p);
		} catch (ProductNotFoundException ex) {
			Logger.getLogger(AddProductFormController.class.getName()).log(Level.SEVERE, null, ex);
		}
		return "product/form";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String processSubmit(@ModelAttribute("product") Product p) {
		productRepository.updateProduct(p);

		return "redirect:/product/list.do";
	}

}
