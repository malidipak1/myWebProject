package com.appsdaily.core.controller;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appsdaily.payment.scm.constants.Constants.PaymentConfig;
import com.appsdaily.util.LogUtils;

/**
 * @author Dipak.Mali
 *
 */
public abstract class ActionHandler implements IAction{
	
	protected String forwardJsp = null;
	
	public void handleAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String result = this.execute(request, response);
		
		if(this.getForwardJsp() != null) {
			String forwardJsp = null;
			if(SUCCESS.equalsIgnoreCase(result)) {
				forwardJsp = this.getForwardJsp();
			} else if(ERROR.equalsIgnoreCase(result)) {
				forwardJsp = PaymentConfig.ERRORJSP;
			} else if(LOGIN.equalsIgnoreCase(result)) {
				forwardJsp = PaymentConfig.ERRORJSP;
			} 
			this.forwardRequest(request, response, forwardJsp);
		} else {
			LogUtils.debug("Call writeToResponse(resp, data)");
		}
	}

	protected void setForwardJsp(String forwardJsp) {
		this.forwardJsp = forwardJsp;
	}
	
	protected String getForwardJsp() {
		return this.forwardJsp;
	}
	
	private void forwardRequest(HttpServletRequest request, HttpServletResponse response, String forwardJsp) {

		if(forwardJsp != null) {
			RequestDispatcher rd = request.getRequestDispatcher(forwardJsp);
			try {
				rd.forward(request, response);
			} catch (Exception e) {
				LogUtils.error("Error calling request forward ", e);
			}
		} else {
			LogUtils.error("[ActionHandler:forwardRequest] Unable to forward to '" + forwardJsp + "'");
			throw new IllegalStateException();
		}
	}

	protected void writeToResponse(HttpServletResponse response, String returndata) {

		try{
			response.setCharacterEncoding("UTF-8");
			if (returndata != null) {
				PrintWriter out = response.getWriter();
				response.setContentType("text/plain; charset=UTF-8");
				response.setContentLength(returndata.length());
				response.setHeader("Content-Length", String.valueOf(returndata.getBytes().length));
				out.println(returndata);
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			LogUtils.error("Error in writing response ", e);
		}
	}
		
	public String getDefaultResponseType() {
		/**
		 * most commands are expected to respond in json
		 */
		String respType = "application/json; charset=UTF-8";
		/**
		 * 
		 * text/html
		 * text/plain
		 * application/xml 
		 * 
		 */
		
		return respType;
	}
	
}
