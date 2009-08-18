/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.melexis.repository;

import com.melexis.InsufficientBalanceException;
import com.melexis.Order;
import java.util.List;

/**
 *
 * @author brh
 */
public interface OrderRepository {

	/**
	 * List all the orders made today.
	 * @return a list of the orders
	 */
	List<Order> findOrdersForToday();

	/**
	 * List all open orders for the given user.
	 * @param username the user for whom to find the orders.
	 * @return a list of orders
	 */
	List<Order> findOrdersForTodayForUser(String username);

	/**
	 * Save a order to the repository
	 * @param t the order to save to the repository
	 * @return the saved order
	 * @throws insufficientBalanceExecption the user does not have
	 *		enough money on his account.
	 */
	Order executeOrder(Order t) throws InsufficientBalanceException;
}
