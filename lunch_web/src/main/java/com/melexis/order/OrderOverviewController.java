/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.melexis.order;

import com.melexis.Order;
import com.melexis.repository.OrderRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author brh
 */
@Controller
@RequestMapping("/order/overview.do")
@SessionAttributes("orders")
public class OrderOverviewController {
	
	private final OrderRepository orderRepository;

	@Autowired
	public OrderOverviewController(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@RequestMapping(method=RequestMethod.GET)
	public String orderOverview(@RequestParam("user") String username, ModelMap mm) {
		List<Order> orders = orderRepository.findOrdersForTodayForUser(username);
		mm.addAttribute("orders", orders);
		return "order/overview";
	}
}
