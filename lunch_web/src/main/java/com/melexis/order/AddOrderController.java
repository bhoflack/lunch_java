/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.melexis.order;

import com.melexis.InsufficientBalanceException;
import com.melexis.Order;
import com.melexis.Product;
import com.melexis.UserProfile;
import com.melexis.repository.OrderRepository;
import com.melexis.repository.ProductRepository;
import com.melexis.repository.UserProfileRepository;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@RequestMapping("/order/add.do")
@SessionAttributes("order")
public class AddOrderController {

	private final OrderRepository orderRepository;
	private final ProductRepository productRepository;
	private final UserProfileRepository userProfileRepository;

	@Autowired
	public AddOrderController(OrderRepository orderRepository,
		ProductRepository productRepository,
		UserProfileRepository userProfileRepository) {
		this.orderRepository = orderRepository;
		this.productRepository = productRepository;
		this.userProfileRepository = userProfileRepository;
	}

	@ModelAttribute("products")
	public List<Product> populateProducts() {
		return productRepository.findAvailableProducts();
	}

	@ModelAttribute("users")
	public List<UserProfile> populateUsers() {
		return userProfileRepository.findAll();
	}

	@RequestMapping(method=RequestMethod.GET)
	public String prepareForm(ModelMap mm) {
		mm.addAttribute("order", new Order());
		return "order/form";
	}

	@RequestMapping(method=RequestMethod.POST)
	public String processForm(@ModelAttribute("order") Order order) {
		try {
			orderRepository.executeOrder(order);
		} catch (InsufficientBalanceException ex) {
			Logger.getLogger(AddOrderController.class.getName()).log(Level.SEVERE, null, ex);
		}
		return "redirect:/order/overview";
	}


}
