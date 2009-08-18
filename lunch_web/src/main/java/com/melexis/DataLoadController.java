/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.melexis;

import com.melexis.repository.ProductRepository;
import com.melexis.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author brh
 */
@Controller
@RequestMapping("/data/load.do")
public class DataLoadController {
	
	private final UserProfileRepository userProfileRepository;
	private final ProductRepository productRepository;
	
	@Autowired
	public DataLoadController(UserProfileRepository userProfileRepository,
		ProductRepository productRepository) {
		this.userProfileRepository = userProfileRepository;
		this.productRepository = productRepository;
	}

	@RequestMapping(method=RequestMethod.GET)
	public String loadData() {
		userProfileRepository.findUserOrCreateNew("brh");
		userProfileRepository.findUserOrCreateNew("abe");

		productRepository.addProduct(new Product("martino", 3.21));
		productRepository.addProduct(new Product("club", 2.99));
		productRepository.addProduct(new Product("ham kaas", 2.8));

		return "redirect:/product/list.do";
	}
	
}
