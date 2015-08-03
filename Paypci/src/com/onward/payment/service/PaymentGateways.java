package com.onward.payment.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.appsdaily.payment.analytics.AnalyticsActionLogDOBean;
import com.appsdaily.payment.db.DBConnection;
import com.appsdaily.payment.db.service.TransactionDAOService;
import com.appsdaily.payment.db.service.impl.TransactionDAOServiceImpl;
import com.appsdaily.payment.scm.constants.Constants;
import com.appsdaily.payment.util.PaymentProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onward.payment.beans.PaymentHelperBean;
import com.onward.payment.beans.UserPaymentDetailsBean;
import com.onward.payment.helpers.Helper;
import com.onward.payment.helpers.PaymentHelper;
import com.onward.payment.helpers.URLConnectionCCAvenue;
import com.onward.scm.exceptions.AppsDailyException;
import com.onward.scm.utility.DateUtils;
import com.tp.pg.util.TransactionRequestBean;

public class PaymentGateways {
	private static ObjectMapper mapper=new ObjectMapper();
	private static Logger log = Logger.getLogger(PaymentGateways.class);
	private static final HashMap<String, String> PaymentSubType = new HashMap<String, String>();
	static {
		PaymentSubType.put("1", "creditCard");
		PaymentSubType.put("2", "debitCard");
		PaymentSubType.put("3", "cashCard");
		PaymentSubType.put("4", "netBank");
		PaymentSubType.put("5", "mobilePay");
	}

	/**
	 * TechProcess
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public static String setRequiredParametersForPaymentGatewayTechProccess(HttpServletRequest request, HttpServletResponse response) {
		String masked_card_info = null;
		HttpSession session = request.getSession(false);
		if (session != null) {
			TransactionRequestBean objTransactionRequestBean = new TransactionRequestBean();
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
			String strITC="";
			String bank_name =  request.getParameter("bank_nameText");
			String client_time=request.getParameter("cl_time");
			String cardTypeTechP = "TWC";
			String token=null;
			strITC=PaymentHelper.setClientMetaDataforTechProcess(paymentHelperBean, strITC);
			UserPaymentDetailsBean userPaymentDetailsBean = PaymentGateways.setParametersByElementNo(request, response,paymentHelperBean);
			/*if(userPaymentDetailsBean.isSave()){
				CardVault cvAction=new CardVault();
				token=cvAction.saveThisCard(userPaymentDetailsBean);
			}*/
			
			
			if (elementNo.equals("4")) {
				cardTypeTechP = "T";
			} else {
				String cardNum = userPaymentDetailsBean.getCardNumber();
				masked_card_info =cardNum.substring(0,4)+"XXXXXXXX"+cardNum.substring(cardNum.length() - 4, cardNum.length());
				objTransactionRequestBean.setCardNo(userPaymentDetailsBean.getCardNumber());
				objTransactionRequestBean.setCardName(userPaymentDetailsBean.getCardName());
				if (userPaymentDetailsBean.getCvvNumber() != null) {
					objTransactionRequestBean.setCardCVV(userPaymentDetailsBean.getCvvNumber());
				}
				if (userPaymentDetailsBean.getExpiryMonth() != null) {
					objTransactionRequestBean.setCardExpMM(userPaymentDetailsBean.getExpiryMonth());
				}
				if (userPaymentDetailsBean.getExpiryYear() != null) {
					objTransactionRequestBean.setCardExpYY(userPaymentDetailsBean.getExpiryYear());
				}
			}
			objTransactionRequestBean.setStrRequestType(cardTypeTechP);
			objTransactionRequestBean.setStrMerchantCode(Constants.MERCHANT_CODE_TP_WEB);
			objTransactionRequestBean.setStrCurrencyCode("INR");
			
			try {
				objTransactionRequestBean.setMerchantTxnRefNumber(paymentHelperBean.getOrder_id());
				objTransactionRequestBean.setStrAmount(paymentHelperBean.getAmount());
				// PaymentSub Type
				strITC=strITC.concat(URLDecoder.decode("pg" + ":" + Constants.MERCHANT_CODE_TP_WEB, "UTF-8")).concat("~");
				strITC=strITC.concat(
						URLDecoder.decode(Constants.PAYMENT_SUB_TYPE + ":"
								+ (PaymentSubType.containsKey(elementNo) ? PaymentSubType.get(elementNo) : ""), "UFT-8")).concat("~");
				strITC=strITC.concat(URLDecoder.decode("info" + ":" + masked_card_info, "UTF-8")).concat("~");
				strITC=strITC.concat(URLDecoder.decode("token" + ":" + token, "UTF-8")).concat("~");
				strITC=strITC.concat( URLDecoder.decode("bank" + ":" +(elementNo.equals( "4" )?(bank_name.replaceAll(" |/","_")):(userPaymentDetailsBean.getIssuingBank().replaceAll(" |/","_"))),"UTF-8"));
				if(Helper.isBlankOrNull(paymentHelperBean.getGroup())){
					strITC=strITC.concat( "~" ).concat( URLDecoder.decode("group:g2","UTF-8"));
				}

			} catch (UnsupportedEncodingException e2) {
				log.error("[PciPaymentService Error] Unable to decode " + e2.getMessage());
			}
			objTransactionRequestBean.setStrITC(strITC.toString());
			objTransactionRequestBean.setStrReturnURL(paymentHelperBean.getRedirectURL());
			objTransactionRequestBean.setStrS2SReturnURL(paymentHelperBean.getCancelURL());
			objTransactionRequestBean.setStrShoppingCartDetails("TEST_" + paymentHelperBean.getAmount() + "_0.00");
			objTransactionRequestBean.setTxnDate(Helper.dateFormatString("dd-MM-yyyy"));
			objTransactionRequestBean.setWebServiceLocator(Constants.WEBSERVICELOCATOR);
			objTransactionRequestBean.setKey(Constants.KEY_TECH_PROCESS_WEB.getBytes());
			objTransactionRequestBean.setIv(Constants.IV_TECH_PROCESS_WEB.getBytes());
			objTransactionRequestBean.setLogPath("/jbossmobicopy/standalone/log/payment/techproccesslogs.log");
			objTransactionRequestBean.setStrBankCode(userPaymentDetailsBean.getCardName());
			ObjectMapper mapper = new ObjectMapper();
			try {
				String requestToObj = mapper.writeValueAsString(objTransactionRequestBean.getClass());
				log.info("[PciPaymentService Info] Request send to techProcess :" + requestToObj);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			long transaction_id = Long.parseLong(paymentHelperBean.getOrder_id());
			String techProccessURL = objTransactionRequestBean.getTransactionToken();
			log.info("[PciPaymentService Info][TID : " + transaction_id + "] TechProcess URL with Token : " + techProccessURL);

			//try(Connection con=DBConnection.getJbossConnection(DBConnection.DB_NAME_PAYMENTS)) {
			try{
				AnalyticsActionLogDOBean bean=new AnalyticsActionLogDOBean();
				//AnalyticsDBInteraction anaDBInteraction=new AnalyticsDBInteraction();
				bean.setPartnerId(paymentHelperBean.getPartner_id());
				bean.setProductId(paymentHelperBean.getProduct_id());
				bean.setGuest_id(paymentHelperBean.getGuest_id());
				bean.setSubscriberId(paymentHelperBean.getSubscriber_id());
				bean.setTimeStamp(Long.parseLong(client_time));
				if (elementNo.equals("4")) {
					bean.setActionId(1351);
					bean.setAp2Value(paymentHelperBean.getOrder_id());
					bean.setAp1Value(bank_name);
					Helper.sendPost("{\"analytics\":[{\"timestamp\":\"" + client_time + "\",\"order_id\":\"" + paymentHelperBean.getOrder_id()
							+ "\",\"bank_name\":\"" + bank_name + "\",\"action_id\":\"1351\"}],\"subinfo\":{},\"validation_info\":{}}",
							PaymentProperties.getAnalyticsUrl(), true);
				} else if (elementNo.equals("2")) {// debit card
					bean.setActionId(1353);
					bean.setAp3Value(paymentHelperBean.getOrder_id());
					bean.setAp2Value("debitCard");
					bean.setAp1Value(userPaymentDetailsBean.getIssuingBank());
					Helper.sendPost("{\"analytics\":[{\"timestamp\":\"" + client_time+ "\",\"order_id\":\"" + paymentHelperBean.getOrder_id()
							+ "\",\"bank_name\":\"debitCard\",\"action_id\":\"1353\",\"card_type\":\"" + userPaymentDetailsBean.getIssuingBank()
							+ "\"}],\"subinfo\":{},\"validation_info\":{}}", PaymentProperties.getAnalyticsUrl(), true);
				} else if (elementNo.equals("1")) {// credit card
					bean.setActionId(1352);
					bean.setAp3Value(paymentHelperBean.getOrder_id());
					bean.setAp2Value("creditCard");
					bean.setAp1Value(userPaymentDetailsBean.getIssuingBank());
					Helper.sendPost("{\"analytics\":[{\"timestamp\":\"" + client_time + "\",\"order_id\":\"" + paymentHelperBean.getOrder_id()
							+ "\",\"bank_name\":\"creditCard\",\"action_id\":\"1352\",\"card_type\":\"" + userPaymentDetailsBean.getIssuingBank()
							+ "\"}],\"subinfo\":{},\"validation_info\":{}}", PaymentProperties.getAnalyticsUrl(), true);
				}else {
					bean.setActionId(1352);
					bean.setAp3Value(paymentHelperBean.getOrder_id());
					bean.setAp2Value(PaymentSubType.containsKey(elementNo)?PaymentSubType.get(elementNo):"");
					bean.setAp1Value(userPaymentDetailsBean.getIssuingBank());
				}
				//anaDBInteraction.insertIntoAnalyticsLogs(bean, con);
			} catch (Exception e) {
				log.error("[PciPaymentService Error] Unable to send Analytics " + e.getMessage());
				e.printStackTrace();
			}
			session.invalidate();
			if ((techProccessURL != null) && techProccessURL.startsWith("https://")) {
				return techProccessURL;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	
	/**
	 * CC Avenue
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unused")
	public static String setRequiredParametersForPaymentGatewayCCEvenue(HttpServletRequest request, HttpServletResponse response) {
		String returnURL = Constants.ORDER_PAYMENT_DETAILS_JSON;
		String masked_card_info = null;
		HttpSession session = request.getSession(false);
		if (session != null) {
			try {
				String hashcode = (String) session.getAttribute("hashcode");
				String decResp = Helper.base64Decode(hashcode);
				/*StringTokenizer tokenizer = new StringTokenizer(decResp, "&");
				String pair = null, pname = null, pvalue = null;
				StringBuilder strITC = new StringBuilder();
				String amount = "";
				String order_id = "";
				String group = "";
				while (tokenizer.hasMoreTokens()) {
					pair = tokenizer.nextToken();
					if (pair != null) {
						StringTokenizer strTok = new StringTokenizer(pair, "=");
						pname = "";
						pvalue = "";
						if (strTok.hasMoreTokens()) {
							pname = strTok.nextToken();
							if (strTok.hasMoreTokens()) {
								pvalue = strTok.nextToken();
							}
							// request.setAttribute(pname,
							// URLDecoder.decode(pvalue));
							
							 * if (pname.equals("redirect_url")) { redirectURL =
							 * URLDecoder.decode(pvalue, "UFT-8"); } else if
							 * (pname.equals("cancel_url")) { cancelURL =
							 * URLDecoder.decode(pvalue, "UFT-8"); } else {
							 
							if (pname.equals("amount")) {
								amount = pvalue;
							}
							if (pname.equals("order_id")) {
								order_id = pvalue;
							}
							if (pname.equals("group")) {
								group = pvalue;
							}
							// strITC.append(URLDecoder.decode(pname + ":" +
							// pvalue, "UFT-8")).append("~");
							// }
						}
					}
				}*/
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
				UserPaymentDetailsBean userPaymentDetailsBean = PaymentGateways.setParametersByElementNo(request, response,paymentHelperBean);
				String elementNo = request.getParameter("elementNo");
				String bank_name = null;
				String card_nameText = null;
				if (elementNo.equals("4")) {
					bank_name = request.getParameter("bank_nameText");
				} else {
					card_nameText = request.getParameter("card_nameTextField");
					String cardNum = userPaymentDetailsBean.getCardNumber();
					masked_card_info = "XXXXXXXXXXXX" + cardNum.substring(cardNum.length() - 4, cardNum.length());
				}

				request.setAttribute("merchant_id", "41815");
				request.setAttribute("currency", "INR");
				request.setAttribute("amount", userPaymentDetailsBean.getAmount());
				request.setAttribute("language", "EN");
				request.setAttribute("order_id", userPaymentDetailsBean.getOrderId());
				request.setAttribute("billing_country", "India");
				request.setAttribute("payment_option", request.getParameter("payment_option"));
				request.setAttribute("card_type", request.getParameter("card_type"));
				request.setAttribute("data_accept", request.getParameter("data_accept"));

				if (!userPaymentDetailsBean.isError()) {
					String cancelURL = Constants.CANCEL_URL_CCEVENUE_JSON;
					String redirectURL = Constants.REDIRECT_URL_CCEVENUE_JSON;
					HashMap<String, String> hashMap = getPaymentDetailsHashMap(request, response);
					Iterator<Entry<String, String>> it = hashMap.entrySet().iterator();
					while (it.hasNext()) {
						Entry<String, String> pairs = (Entry<String, String>) it.next();
						// System.out.println(pairs.getKey() + " = " +
						// pairs.getValue());
						if (!((String) pairs.getKey()).equals("amount") && !((String) pairs.getKey()).equals("order_id")) {
							if (((String) pairs.getKey()).equals("cancel_url")) {
								cancelURL = (String) pairs.getValue();
							} else if (((String) pairs.getKey()).equals("redirect_url")) {
								redirectURL = (String) pairs.getValue();
							} else {
								request.setAttribute((String) pairs.getKey(), (String) pairs.getValue());
							}
						}
						it.remove(); // avoids a ConcurrentModificationException
					}
					request.setAttribute("cancel_url", cancelURL);
					request.setAttribute("redirect_url", redirectURL);
					long transaction_id = Long.parseLong(userPaymentDetailsBean.getOrderId());
					log.info("[PciPaymentService Info][TID : " + transaction_id + "] CCAvenue URL :" );

					try {
						if (elementNo.equals("4")) {
							Helper.sendPost("{\"analytics\":[{\"timestamp\":\"" + DateUtils.getCurrentTimeInMilliSeconds() + "\",\"order_id\":\"" + userPaymentDetailsBean.getOrderId()
									+ "\",\"bank_name\":\"" + bank_name + "\",\"action_id\":\"1351\"}],\"subinfo\":{},\"validation_info\":{}}",
									PaymentProperties.getAnalyticsUrl(), true);
						} else if (elementNo.equals("2")) {// debit card
							Helper.sendPost("{\"analytics\":[{\"timestamp\":\"" + DateUtils.getCurrentTimeInMilliSeconds() + "\",\"order_id\":\"" + userPaymentDetailsBean.getOrderId()
									+ "\",\"bank_name\":\"debit_card\",\"action_id\":\"1353\",\"card_type\":\"" + card_nameText
									+ "\"}],\"subinfo\":{},\"validation_info\":{}}", PaymentProperties.getAnalyticsUrl(), true);
						} else if (elementNo.equals("1")) {// credit card
							Helper.sendPost("{\"analytics\":[{\"timestamp\":\"" + DateUtils.getCurrentTimeInMilliSeconds() + "\",\"order_id\":\"" + userPaymentDetailsBean.getOrderId()
									+ "\",\"bank_name\":\"credit_card\",\"action_id\":\"1352\",\"card_type\":\"" + card_nameText
									+ "\"}],\"subinfo\":{},\"validation_info\":{}}", PaymentProperties.getAnalyticsUrl(), true);
						}
					} catch (Exception e) {
						log.error("[PciPaymentService Error] Unable to send Analytics " + e.getMessage());
						e.printStackTrace();
					}

					try (Connection con = DBConnection.getConnection()) {
						TransactionDAOService dao = new TransactionDAOServiceImpl();
						dao.updateTransactionLevel(transaction_id, Constants.REQUEST_BANK_PAYMENT_TECHPROCESS, con);
					} catch (AppsDailyException e) {
						log.error("[PciPaymentService Error] Unable get Connection " + e.getErrorMessage());
					} catch (Exception e) {
						log.error("[PciPaymentService Error] Unable get Connection" + e.getMessage());
					}
					
				} else {
					request.setAttribute("error_msg", userPaymentDetailsBean.getErrorMsg());
					returnURL = Constants.PAYMENT_PAGE;
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Error-setRequiredParametersForPaymentGatewayCCEvenue :", e);
			}
			// System.out.println("Return url = "+Constants.ORDER_PAYMENT_DETAILS_JSON);
			return returnURL;
		} else {
			return null;
		}
	}
	
	
	public static UserPaymentDetailsBean setParametersByElementNo(HttpServletRequest request, HttpServletResponse response, PaymentHelperBean paymentHelperBean) {
		String elementNo = request.getParameter("elementNo");
		UserPaymentDetailsBean detailsBean = new UserPaymentDetailsBean();
		/*if ((request.getParameter("subscriber_id") == null) || request.getParameter("subscriber_id").trim().equals("")) {
			detailsBean.setSubscriberId(0l);
		} else {
			detailsBean.setSubscriberId(Long.parseLong(request.getParameter("subscriber_id")));
		}*/
		detailsBean.setPartner_id(paymentHelperBean.getPartner_id());
		detailsBean.setProduct_id(paymentHelperBean.getProduct_id());
		detailsBean.setSubId(paymentHelperBean.getSubscriber_id());
		detailsBean.setGuestId(paymentHelperBean.getGuest_id());
		detailsBean.setOrderId(paymentHelperBean.getOrder_id());
		detailsBean.setAmount(paymentHelperBean.getAmount());
		detailsBean.setPaymentOption(request.getParameter("payment_option"));
		detailsBean.setDataAccept(request.getParameter("data_accept"));

		if (elementNo.equals("1")) {

			if ((request.getParameter("card_name1") != null) && request.getParameter("card_name1").equalsIgnoreCase("Amex ezeClick")) {
				request.setAttribute("card_name", request.getParameter("card_name1"));
				detailsBean.setCardName(request.getParameter("card_name1"));
			} else {
				request.setAttribute("card_name", request.getParameter("card_name1"));
				request.setAttribute("card_number", request.getParameter("card_number1"));
				request.setAttribute("cvv_number", request.getParameter("cvv_number1"));
				request.setAttribute("expiry_month", request.getParameter("expiry_month1"));
				request.setAttribute("expiry_year", request.getParameter("expiry_year1"));
				request.setAttribute("issuing_bank", request.getParameter("issuing_bank1"));

				detailsBean.setCardName(request.getParameter("card_name1"));
				detailsBean.setCardNumber(request.getParameter("card_number1"));
				detailsBean.setCvvNumber(request.getParameter("cvv_number1"));
				detailsBean.setExpiryMonth(request.getParameter("expiry_month1"));
				detailsBean.setExpiryYear(request.getParameter("expiry_year1"));
				detailsBean.setIssuingBank(request.getParameter("issuing_bank1"));
			}
			String save_this_card=request.getParameter("save_this_card1");
			detailsBean.setSave(save_this_card!=null?true:false);
			detailsBean.setCard_category("creditCard");
		} else if (elementNo.equals("2")) {

			if ((request.getParameter("data_accept") != null) && request.getParameter("data_accept").equalsIgnoreCase("Y")) {
				request.setAttribute("card_name", request.getParameter("card_name2"));
				request.setAttribute("card_number", request.getParameter("card_number2"));
				request.setAttribute("cvv_number", request.getParameter("cvv_number2"));
				request.setAttribute("expiry_month", request.getParameter("expiry_month2"));
				request.setAttribute("expiry_year", request.getParameter("expiry_year2"));
				request.setAttribute("issuing_bank", request.getParameter("issuing_bank2"));

				detailsBean.setCardName(request.getParameter("card_name2"));
				detailsBean.setCardNumber(request.getParameter("card_number2"));
				if ((request.getParameter("cvv_number2") != null) && !request.getParameter("cvv_number2").equalsIgnoreCase("0")) {
					detailsBean.setCvvNumber(request.getParameter("cvv_number2"));
				} else {
					detailsBean.setCvvNumber("");
				}
				if ((request.getParameter("expiry_month2") != null) && !request.getParameter("expiry_month2").equalsIgnoreCase("0")) {
					detailsBean.setExpiryMonth(request.getParameter("expiry_month2"));
				} else {
					detailsBean.setExpiryMonth("");
				}
				if ((request.getParameter("expiry_year2") != null) && !request.getParameter("expiry_year2").equalsIgnoreCase("0")) {
					detailsBean.setExpiryYear(request.getParameter("expiry_year2"));
				} else {
					detailsBean.setExpiryYear("");
				}
				detailsBean.setIssuingBank(request.getParameter("issuing_bank2"));
				
				String save_this_card=request.getParameter("save_this_card2");
				detailsBean.setSave(save_this_card!=null?true:false);
				
			} else {
				request.setAttribute("card_name", request.getParameter("card_name2"));
				detailsBean.setCardName(request.getParameter("card_name2"));
			}
			detailsBean.setCard_category("debitCard");
		} else if (elementNo.equals("3")) {

			request.setAttribute("card_name", request.getParameter("card_name3"));
			detailsBean.setCardName(request.getParameter("card_name3"));

		} else if (elementNo.equals("4")) {

			request.setAttribute("card_name", request.getParameter("card_name4"));
			detailsBean.setCardName(request.getParameter("card_name4"));

		} else {

			request.setAttribute("card_name", request.getParameter("card_name5"));
			request.setAttribute("mobile_number", request.getParameter("mobile_number"));
			detailsBean.setCardName(request.getParameter("card_name5"));
			detailsBean.setMobile(request.getParameter("mobile_number"));

		}
		return detailsBean;
	}

	public static String getJsonDataForPaymentPage(HttpServletRequest request, HttpServletResponse response) {

		String strurl = "https://secure.ccavenue.com/transaction/transaction.do?command=getJsonData&access_code=" + Constants.ACCESS_CODE_CCAVENUE
				+ "&currency=INR&amount=" + request.getParameter("amount");
		String responseStr = "failed";
		try {
			responseStr = URLConnectionCCAvenue.HttpURLConnectionrequest(strurl, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseStr;
	}

	public static String getPaymentPage(HttpServletRequest request, HttpServletResponse response) {
		String responseStr = Constants.PAYMENT_PAGE;
		try {
			String encResp = request.getParameter("hashcode");
			request.setAttribute("hashcode", URLDecoder.decode(encResp, "UTF-8"));
			String decResp = Helper.base64Decode(encResp);
			StringTokenizer tokenizer = new StringTokenizer(decResp, "&");
			String pair = null, pname = null, pvalue = null;
			while (tokenizer.hasMoreTokens()) {
				pair = tokenizer.nextToken();
				if (pair != null) {
					StringTokenizer strTok = new StringTokenizer(pair, "=");
					pname = "";
					pvalue = "";
					if (strTok.hasMoreTokens()) {
						pname = strTok.nextToken();
						if (strTok.hasMoreTokens()) {
							pvalue = strTok.nextToken();
						}
						request.setAttribute(pname, URLDecoder.decode(pvalue, "UTF-8"));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseStr;
	}

	/*
	 * For tp
	 */

	public static String getPaymentPageTP(HttpServletRequest request, HttpServletResponse response) {
		String responseStr = Constants.PAYMENT_PAGETP;
		try {
			HttpSession session = request.getSession(true);
			String encResp = request.getParameter("hashcode");
			session.setAttribute("hashcode", URLDecoder.decode(encResp, "UTF-8"));
			String decResp = Helper.base64Decode(encResp);
			StringTokenizer tokenizer = new StringTokenizer(decResp, "&");
			String pair = null, pname = null, pvalue = null;
			while (tokenizer.hasMoreTokens()) {
				pair = tokenizer.nextToken();
				if (pair != null) {
					StringTokenizer strTok = new StringTokenizer(pair, "=");
					pname = "";
					pvalue = "";
					if (strTok.hasMoreTokens()) {
						pname = strTok.nextToken();
						if (strTok.hasMoreTokens()) {
							pvalue = strTok.nextToken();
						}
						request.setAttribute(pname, URLDecoder.decode(pvalue, "UTF-8"));
					}
				}
			}
			request.setAttribute("banking_type",request.getParameter("banking_type"));
			request.setAttribute("product_details", request.getParameter("product_details"));
			request.setAttribute("product_name", request.getParameter("product_name"));
			request.setAttribute("order_time",
					URLDecoder.decode(DateUtils.convertTimeInMilliSecondsToDate(System.currentTimeMillis(), "dd-MMM-yyy hh:mm a"), "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseStr;
	}

	public static HashMap<String, String> getPaymentDetailsHashMap(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		try {
			String encResp = request.getParameter("hashcode");
			String decResp = Helper.base64Decode(encResp);
			StringTokenizer tokenizer = new StringTokenizer(decResp, "&");
			String pair = null, pname = null, pvalue = null;
			while (tokenizer.hasMoreTokens()) {
				pair = tokenizer.nextToken();
				if (pair != null) {
					StringTokenizer strTok = new StringTokenizer(pair, "=");
					pname = "";
					pvalue = "";
					if (strTok.hasMoreTokens()) {
						pname = strTok.nextToken();
						if (strTok.hasMoreTokens()) {
							pvalue = strTok.nextToken();
						}
						hashMap.put(pname, URLDecoder.decode(pvalue, "UTF-8"));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hashMap;
	}
	
	public static void main(String[] args) {
		String s="159fdd9038f15182abd71786a806446cc5070be0fede9250bf8c0045ef8ac1fe3f0f496038fcaa5897faab09cdd866014629c95152b4c141d3ea06802efbdf6e3c4081e11fe31a677bf5fdd73a5f0f63d0542ccf90e6cc426f384707879e666e46d2cf3744666313220a091bb3714fe5a5fbf2e7ce5ea3b1eab225d3ecaa188160731a12b168c3e7f3ba44808e314763a03e4a973e9dee6f0839fa6709a2115f884275b5da70e4c1079090b22d93861d142cea1b73aff1ea6fa427ca5dda3d4f1aa63cb1ae19630fb67e2e258e82b318626b13bfde3430a1694b0ebdb5b7c84af71bc691bd4f1f2de7f7e6851cb4c60af1663e4fd1ee39fb545973881ff968ab1ecac32066033031434ce67d9e5415a1b66c838a63acc615d19a702493964913e9537d008beab07208b99a47f74a2a397358f18ceee7a7cc0e139fc39f8ebd91a99a9cd27f5c18052508385a947d9efc";
		String s2="order_id=50347323&amount=3999.0&merchant_param1=0&merchant_param2=new&merchant_param3=2&merchant_param4=33083&cancel_url=http://appsdaily.in/edu/content/paymentSuccessTP.jsp&redirect_url=http://appsdaily.in/edu/content/paymentSuccessTP.jsp&group=undefined&subscriber_id=0";
		
		String decResp = Helper.base64Decode(s);
		System.out.println(decResp);
	}

}
