package com.onward.payment.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.appsdaily.core.controller.ActionHandler;
import com.appsdaily.payment.scm.constants.Constants;
import com.appsdaily.util.LogUtils;
import com.appsdaily.util.Util;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onward.payment.beans.PayU;
import com.onward.payment.beans.PaymentHelperBean;
import com.onward.payment.helpers.Helper;
import com.tp.pg.util.TransactionRequestBean;

public class PayUAction extends ActionHandler {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		LogUtils.info("forward -> Post data to Payu URL");

		/*
		@SuppressWarnings("unchecked")
		Map<String, String[]> map = (Map<String, String[]>) request.getParameterMap();
		
		Set<String> set = (Set<String>)map.keySet();
		
		Iterator<String> it = set.iterator();
		while(it.hasNext()) {
			String entry = it.next();
			try {
				String []a = (map.get(entry));
				LogUtils.info(entry + " = " + a.length + " - " + a[0]);
				
			} catch (Exception e) {
				LogUtils.error("Error caused: " + e);
			}
		}
		*/
		ObjectMapper mapper=new ObjectMapper();
		HttpSession session = request.getSession(false);
		if (session != null) {
			String elementNo = request.getParameter("elementNo");
			String hashcode = (String) session.getAttribute("hashcode");
			String decResp = Helper.base64Decode(hashcode);
			decResp="{\""+decResp;
			decResp=decResp.replaceAll("&","\",\"");
			decResp=decResp.replaceAll("=","\":\"");
			decResp=decResp.concat("\"}");
			
			PaymentHelperBean paymentHelperBean=new PaymentHelperBean();
			try {
				paymentHelperBean=mapper.readValue(decResp,PaymentHelperBean.class);
			} catch (IOException e3) {
				e3.printStackTrace();
			}
				
		PayU payu = new PayU();
		payu.setFirstName("User ID" + paymentHelperBean.getSubscriber_id());
		payu.setEmail("merchant@appsdaily.in");
		payu.setMobile("9876543200");
		payu.setKey(Constants.PAYU_KEY);
		payu.setProdInfo(Helper.nullToBlank(request.getParameter("product_name")));
		payu.setfUrl("http://localhost:8080/pcipayments/paymentAction?action=payu_response");//paymentHelperBean.getCancelURL()
		payu.setsUrl("http://localhost:8080/pcipayments/paymentAction?action=payu_response");//paymentHelperBean.getRedirectURL()
		
		payu.setOrderId(paymentHelperBean.getOrder_id());
		payu.setOrderId("CS" + String.valueOf(Math.round(Math.random() * 100000)));
		
		payu.setUdf1(paymentHelperBean.getMerchant_param1());
		payu.setUdf2(paymentHelperBean.getMerchant_param2());
		payu.setUdf3(paymentHelperBean.getMerchant_param3());
		payu.setUdf4(paymentHelperBean.getMerchant_param4());
		payu.setUdf5(paymentHelperBean.getMerchant_param5());
		
		payu.setAmount(paymentHelperBean.getAmount());
		String pg = "CC";
		String bankCode = "CC";
		if(request.getParameter("card_type") != null) {
			if("DBCRD".equalsIgnoreCase(request.getParameter("card_type"))) {
				pg = "DC"; // Debit Card
				bankCode = "MAST";
			} else if("CRDC".equalsIgnoreCase(request.getParameter("card_type"))) {
				pg = "CC"; // Credit Card
				bankCode = "CC";
			} else if("NBK".equalsIgnoreCase(request.getParameter("card_type"))) {
				pg = "NB"; // Net Banking
				bankCode = "AXIB";
			}
			//scope for CASH and EMI
		}
		payu.setBankCode(bankCode);//ICICI, AXIB = AXIS, BOMB = Bank of Maharashtra, CABB= Canara Bank, SBI=SBIB
		payu.setPg(pg);
		
		payu.setCardName("User Name");//request.getParameter("card_name" + elementNo) - gives name of the card and not the name on customer card 
		payu.setCardNumber(request.getParameter("card_number" + elementNo));
		payu.setCvvNumber(request.getParameter("cvv_number" + elementNo));
		payu.setExpiryMonth(request.getParameter("expiry_month" + elementNo));
		payu.setExpiryYear(request.getParameter("expiry_year" + elementNo));
		//payu.setIssuingBank(request.getParameter("issuing_bank"));
		
		payu.generateHash();
		request.setAttribute("payuObj", payu);

		forwardJsp = "payu.jsp";
		setForwardJsp(forwardJsp);
		
		//writeToResponse(response, "Successful forward -> Post data to Payu URL");
		return SUCCESS;
		} 
		LogUtils.error("Session Timed Out");
		return FAILED;
	}

}
