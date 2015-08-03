
package com.appsdaily.payment.scm.constants;

public class Constants {
	// -------------------------------------------------- For All Env
	// -------------------------------------------------- //

	public static final String CANCEL_PAGE = "success_techprocess.jsp";
	public static final String SUCCESS_PAGE = CANCEL_PAGE;
	public static final String PAYMENT_PORTAL = "/payment-portalwithTpWeb.jsp";

	// Live
	public static final String KEY_TECH_PROCESS_WEB = "2632233902NMHTWJ";
	public static final String IV_TECH_PROCESS_WEB = "9705449368APYGLD";
	public static final String WEBSERVICELOCATOR = "https://www.tpsl-india.in/PaymentGateway/services/TransactionDetailsNew";
	public static final String MERCHANT_CODE_TP_WEB = "L4809";
	public static final String PAYNIMOURL = "https://www.paynimo.com/mobile/adpm.req?RequestType=PM&MerchantIdentifier=L4807";

	// UAT
/*	 public static final String MERCHANT_CODE_TP_WEB = "T3135";
	 public static final String KEY_TECH_PROCESS_WEB = "6990821712YYTHYJ";
	 public static final String IV_TECH_PROCESS_WEB = "3185644610QQXTOG";
	 public static final String WEBSERVICELOCATOR
	 ="https://www.tekprocess.co.in/PaymentGateway/services/TransactionDetailsNew";
	 public static final String PAYNIMOURL =
	 "https://www.tekprocess.co.in/mobile/AppsDailyPM.jsp?RequestType=PM&MerchantIdentifier=T3135";
*/
	public static final String LINKING_URL = "https://www.tpsl-india.in/GatewayService/services/Transaction?wsdl";
	// IAPYY DETAILS
	public static final String MERCHANT_KEY = "sMWrujiA9yr5XJJ1fHszow";
	public static final String APPLICATION_KEY = "3X7pQbGUUPpclbi5LIF77A";
	// Key for callback functions
	public static final String KEY1 = "CD84588CDBE14893B2E81FBE900CEC";
	// Status
	public static final String STATUS_INCOMPLETE = "incomplete";
	public static final String STATUS_SUCCESS = "success";
	public static final String STATUS_FAIL = "fail";
	// String Constants
	public static final String TRANSACTION_TYPE = "transaction_type";
	public static final String GETFROMREQUEST = "getfromrequest";
	public static final String GETFROMSESSION = "getfromsession";
	public static final String ERROR_STRING = "error";
	public static final String ACTION = "action";
	public static final String KILL = "kill";
	public static final String SUCCESS = "success";
	public static final String OPERATOR = "operator";
	public static final String MOBILE = "mobileno";
	public static final String DATA = "data";
	public static final String DATA_STRING = "data_string";
	public static final String OTP_STRING = "otp";
	public static final String WALLET_VOUCHERS = "vouchers";
	public static final String BALANCE = "balance";
	public static final String REQUESTBEAN = "requestBean";

	// Transaction request type
	public static final String RECHARGE_SC = "recharge_sc";
	public static final String RECHARGE_IPAYY = "recharge_op";
	public static final String RECHARGE_CC = "recharge_bank";
	public static final String RECHARGE_TPWEB = "recharge_bank2";// tp web
																	// wallet
	public static final String PAY_WALLET_CREDIT = "pay_wallet_credit";
	public static final String PAY_WALLET_CASH = "pay_wallet_cash";
	public static final String PAY_IPAYY = "pay_op";
	public static final String PAY_BANK0 = "pay_bank";// cc
	public static final String PAY_BANK1 = "pay_bank1";// tp native
	public static final String PAY_BANK2 = "pay_bank2";// tp web sdk/oneapp
	public static final String RECHARGE_OTHER = "recharge_others";
	public static final String PAYMENT_OTHER = "payment_others";
	public static final String WALLET = "payment_wallet";
	public static final String PAY_THIRD_PARTY = "tp_sc";
	public static final String SENDOTP = "generateOtp";
	public static final String CONFIRMPAYMENT = "confirmPayment";
	public static final String CONFIRMCC = "confirmccavenue";
	public static final String REDIRECT_CC = "redirectTocc";
	public static final String REDIRECT_TP = "redirectTotp";
	public static final String GETPAYPAGETP = "getpaymentpagetp";
	// Levels
	public static final String REQUEST_INITIALIZED = "TransactionInitialized";

	// public static final String REQUEST_FOR_OP_PAYMENT_PAGE
	// ="AD_OpPaymentPage";
	// public static final String
	// REQUEST_FOR_BANK_PAYMENT_PAGE="AD_BankPaymentPage";

	public static final String REQUEST_FOR_OTP_IPAYY = "Otp_Ipayy";
	public static final String REQUEST_OP_PAYMENT_IPAYY = "OpPayment_Ipayy";

	public static final String REQUEST_BANK_PAYMENT_AD = "Payment_AD";
	public static final String REQUEST_BANK_PAYMENT_TECHPROCESS = "Payment_TechProcess";

	public static final String REQUEST_END_TRANSACTION = "EndTransaction";
	// public static final String END_TRANSACTION_INVALID_ACCESS =
	// "invalid_access";
	// public static final String RECHARGE_SC_FAILED = "sc_failed";
	// public static final String RECHARGE_SC_SUCCESS = "sc_success";
	// public static final String VOUCHER_SUCCESS = "voucher_success";
	// public static final String VOUCHER_FAIL = "voucher_fail";
	public static final String CARD_VOUCHER_USED = "Third Party Voucher Used";
	public static final String SCRATCH_CODE_USED = "This Scratch Code is already used";
	public static final String FROMAPPSDALY = " on AppsDaily";
	public static final String PAYMENT_SUB_TYPE = "pay_sub_type";
	public static final String ONLYBANK = "bank";
	public static final String ONLYOP = "operator";
	public static final String BANK_OP = "bank_operator";
	public static final double IPAYYLIMIT = 500.00;
	public static final int HOURSINADAY = 24;
	public static final long MILLIINADAY = 86400000;
	public static final Object INFO = "info";
	public static final Object BANK = "bank";
	public static final String CONFIRMPAYMENTVIATP = "confirmviatp";

	// Gateway Constants
	/*
	 * Live
	 */
	// public static final String DEV_ENVIRONMENT = "LIVE";
	// public static final String
	// PROJECT_URL="https://www.appsdailyworld.com/payment/";
	// public static final String ACCESS_CODE_CCAVENUE = "AVFG02BJ73AD30GFDA";
	// public static final String ENC_KEY_CCEVENUE =
	// "583611819FE709D2AA7303073C2E5FB3";
	// public static final String
	// ANALYTICS_SUBMIT="http://www.appsdaily.info/api/rs/analytics";

	/*
	 * UAT
	 */
	public static final String DEV_ENVIRONMENT = "UAT";
	public static final String PROJECT_URL = "https://203.115.126.233/payment/";
	public static final String ACCESS_CODE_CCAVENUE = "AVFG02BJ73AD30GFDA"; 
	public static final String ENC_KEY_CCEVENUE = "583611819FE709D2AA7303073C2E5FB3";
	public static final String ANALYTICS_SUBMIT = "http://203.115.126.233/analyticswebservice/api/rs/analytics";

	// ================= Common Env Parameters ===============//
	public static final String ORDER_PAYMENT_DETAILS = "/bank0/paymentgateway.jsp";
	public static final String ORDER_PAYMENT_DETAILS_JSON = "/bank0/paymentRequestHandler.jsp";
	public static final String CANCEL_URL_CCEVENUE = PROJECT_URL + "/bank0/paymentSuccessCCEvenue.jsp";
	public static final String REDIRECT_URL_CCEVENUE = PROJECT_URL + "/bank0/paymentSuccessCCEvenue.jsp";
	public static final String CANCEL_URL_CCEVENUE_JSON = PROJECT_URL + "/bank0/paymentResponseHandler.jsp";
	public static final String REDIRECT_URL_CCEVENUE_JSON = PROJECT_URL + "/bank0/paymentResponseHandler.jsp";
	public static final String PAYMENT_PAGETP = "appsdailyPaymentTP.jsp";
	public static final String PAYMENT_PAGE = "appsdailyPayment.jsp";

	/**
	 * Added by Dipak Mali
	 */
	
	public static final String PAYU_Url = "https://test.payu.in/_payment";
	public static final String PAYU_KEY = "gtKFFx";
	public static final String PAYU_HASH_SALT = "eCwWELxi";//test11
	
	
	public static class PaymentConfig {
		public static final String ERRORJSP = "error.jsp";
		public static final String PAYGATEWAY_ACTION = PAYGATEWAY.PAYU.getName();
		
	}
	public static enum PAYGATEWAY {
		TECHPROCESS ("paymentgatewayjsontp"), PAYU("forwardPayU");
		
		private String payType;
		
		PAYGATEWAY(String payType ) {
			this.payType = payType;
		}
		public String getName() {
			return payType;
		}
	}
}
