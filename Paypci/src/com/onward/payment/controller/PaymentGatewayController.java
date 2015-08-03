package com.onward.payment.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.onward.payment.beans.JsonBankData;
import com.onward.payment.helpers.Helper;
import com.onward.payment.service.PaymentGateways;

public class PaymentGatewayController extends HttpServlet {

	/**
     *
     */
	private static final long serialVersionUID = 1L;
	ServletConfig config;
	Logger log = null;
	Hashtable<String, Object> hashtable;
	private static JsonBankData jsonBankData = new JsonBankData();
	@Override
	public void init(ServletConfig config) throws ServletException {

		super.init(config);
		log = Logger.getLogger(this.getClass().getName());
		this.config = config;

	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String action = Helper.nullToBlank(request.getParameter("action"));
			if (log.isDebugEnabled()) {
				log.debug("Action: " + action);
			}
			if ((action == null) || action.equals("")) {
				action = "index";
			}
			if (action.equalsIgnoreCase("paymentgatewayjsoncc")) {
				 forwardRequest( request, response,
				 PaymentGateways.setRequiredParametersForPaymentGatewayCCEvenue( request, response ) );
			} else if (action.equalsIgnoreCase("getdatajson")) {
				 String returndata = PaymentGateways.getJsonDataForPaymentPage( request, response );
				 writeToResponse( response, returndata );
				 //String returndata = jsonBankData.getJsonData();
				 //writeToResponse(response, returndata);
			} else if (action.equalsIgnoreCase("getpaymentpage")) {
				// forwardRequest( request, response,
				// PaymentGatewayAuth.getPaymentPage( request, response ) );
				// log.info(
				// "[PciPaymentService Info] Sending request to paymentDetails page"
				// );
			} else if (action.equalsIgnoreCase("getpaymentpagetp")) {
				forwardRequest(request, response, PaymentGateways.getPaymentPageTP(request, response));
				log.info("[PciPaymentService Info] Sending request to paymentDetails page from TP");
			} else if (action.equalsIgnoreCase("getdatajsontp")) {
				String returndata = jsonBankData.getJsonData();
				writeToResponse(response, returndata);
			} else if (action.equalsIgnoreCase("paymentgatewayjsontp")) {
				String returnUrl = PaymentGateways.setRequiredParametersForPaymentGatewayTechProccess(request, response);
				if (returnUrl == null) {
					returnUrl = PaymentGateways.getPaymentPageTP(request, response);
					forwardRequest(request, response, returnUrl);
				} else {
					response.sendRedirect(returnUrl);
				}

			}

		} catch (Exception e) {
			log.error("[PciPaymentService Error] Error inside doPost of PaymentGatewayContoller Sending to index.jsp :", e);
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		}
	}

	public void forwardRequest(HttpServletRequest request, HttpServletResponse response, String forwardPage) {

		RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
		try {
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void writeToResponse(HttpServletResponse response, String returndata) {

		try {
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
		} catch (IllegalStateException e) {
		} catch (Exception e) {
		}
	}



}
