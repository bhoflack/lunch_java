/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.melexis.order;

import com.melexis.Order;
import com.melexis.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author brh
 */
@Controller
@SessionAttributes("order")
public class AddOrderController {

	private final OrderRepository orderRepository;

	@Autowired
	public AddOrderController(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public String prepareForm(ModelMap mm) {
		mm.addAttribute("order", new Order());
		return "order/form";
	}


}
