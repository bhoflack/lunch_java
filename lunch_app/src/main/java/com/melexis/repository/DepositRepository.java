/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.melexis.repository;

import com.melexis.Deposit;

/**
 *
 * @author brh
 */
public interface DepositRepository {

	/**
	 * Save a deposit to the repository.
	 * @param deposit the deposit
	 * @return the saved deposit
	 */
	Deposit deposit(Deposit deposit);
}
