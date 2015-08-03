package com.onward.payment.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appsdaily.core.controller.ActionHandler;
import com.appsdaily.util.LogUtils;
import com.appsdaily.util.Util;
import com.onward.payment.beans.JsonBankData;

public class ActionMapper {
	private static Map<String, ActionHandler> actionMap = new HashMap<String, ActionHandler>();

	static {
		try {
			actionMap.put("getPaymentPageTP", new PaymentAction());
			actionMap.put("paymentgatewayjsontp", new TechProcessAction());
			actionMap.put("forwardPayU", new PayUAction());
			
			actionMap.put("payu_response", new ActionHandler() {
				
				@Override
				public String execute(HttpServletRequest request,
						HttpServletResponse response) throws Exception {
					writeToResponse(response, Util.getXmlFromMap(request.getParameterMap(), "response"));
					return SUCCESS;
				}
			});
			/**
			 * Ajax
			 */
			actionMap.put("getdatajsontp", new ActionHandler() {
				@Override
				public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
					writeToResponse(response, JsonBankData.getJsonData());
					return SUCCESS;
				}
			});
		} catch (Exception e) {
			LogUtils.error("Something went wrong with the request.", e);
			throw new RuntimeException("Something went wrong with the request.");
		}
	}
	
	
	
	public static ActionHandler getActionHandler (String actionName) {
		return actionMap.get(actionName);
	}

}
