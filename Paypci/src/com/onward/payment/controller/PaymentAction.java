/**
 * @author Dipak.Mali
 *
 */
package com.onward.payment.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appsdaily.core.controller.ActionHandler;
import com.appsdaily.util.LogUtils;
import com.onward.payment.service.PaymentGateways;

public class PaymentAction extends ActionHandler{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LogUtils.info("Executing PaymentAction ");
		setForwardJsp(PaymentGateways.getPaymentPageTP(request, response));
		//setForwardJsp( Constants.PAYMENT_PAGETP);
		return SUCCESS;
	}
	
}
