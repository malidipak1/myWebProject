package com.onward.payment.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appsdaily.core.controller.ActionHandler;
import com.appsdaily.util.LogUtils;
import com.onward.payment.service.PaymentGateways;

public class TechProcessAction extends ActionHandler{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String returnUrl = PaymentGateways.setRequiredParametersForPaymentGatewayTechProccess(request, response);
		if (returnUrl == null) {
			//returnUrl = PaymentGateways.getPaymentPageTP(request, response);
			//forwardRequest(request, response, returnUrl);
			setForwardJsp(PaymentGateways.getPaymentPageTP(request, response));
		} else {
			LogUtils.info("forwarding to TechProcess Payment Gateway");
			response.sendRedirect(returnUrl);
		}
		return SUCCESS;
	}

}
