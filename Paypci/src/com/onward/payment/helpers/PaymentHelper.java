package com.onward.payment.helpers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.onward.payment.beans.PaymentHelperBean;

public class PaymentHelper {
	public static String setClientMetaDataforTechProcess(PaymentHelperBean bean,String strITC){
		try {
			strITC=strITC.concat(URLDecoder.decode("amount:" + bean.getAmount(), "UFT-8")).concat("~");
			strITC=strITC.concat(URLDecoder.decode("order_id:" + bean.getOrder_id(), "UFT-8")).concat("~");
			if(!("".equals(bean.getGroup()))){strITC=strITC.concat(URLDecoder.decode("group:" + bean.getGroup(), "UFT-8")).concat("~");}
			strITC=strITC.concat(URLDecoder.decode("subscriber_id:" + bean.getSubscriber_id(), "UFT-8")).concat("~");
			if(bean.getGuest_id()!=0){strITC=strITC.concat(URLDecoder.decode("guest_id:" + bean.getGuest_id(), "UFT-8")).concat("~");}
			if(bean.getPartner_id()!=0){strITC=strITC.concat(URLDecoder.decode("partner_id:" + bean.getPartner_id() , "UFT-8")).concat("~");}
			if(bean.getProduct_id()!=0){strITC=strITC.concat(URLDecoder.decode("product_id:" + bean.getProduct_id(), "UFT-8")).concat("~");}
			if(bean.getMerchant_param1()!=null){ strITC=strITC.concat(URLDecoder.decode("merchant_param1:" + bean.getMerchant_param1(), "UFT-8")).concat("~");}
			if(bean.getMerchant_param1()!=null){strITC=strITC.concat(URLDecoder.decode("merchant_param2:" + bean.getMerchant_param2(), "UFT-8")).concat("~");}
			if(bean.getMerchant_param1()!=null){strITC=strITC.concat(URLDecoder.decode("merchant_param3:" + bean.getMerchant_param3(), "UFT-8")).concat("~");}
			if(bean.getMerchant_param1()!=null){strITC=strITC.concat(URLDecoder.decode("merchant_param4:" + bean.getMerchant_param4(), "UFT-8")).concat("~");}
			if(bean.getMerchant_param1()!=null){strITC=strITC.concat(URLDecoder.decode("merchant_param5:" + bean.getMerchant_param5(), "UFT-8")).concat("~");}
			if(bean.getMerchant_param1()!=null){strITC=strITC.concat(URLDecoder.decode("merchant_param6:" + bean.getMerchant_param6(), "UFT-8")).concat("~");}
/*			strITC=strITC.concat(URLDecoder.decode("pay_sub_type:" + bean.getPay_sub_type(), "UFT-8")).concat("~");
			strITC=strITC.concat(URLDecoder.decode("info:" + bean.getInfo(), "UFT-8")).concat("~");
			strITC=strITC.concat(URLDecoder.decode("bank:" + bean.getBank(), "UFT-8"));*/
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		
		return strITC;
		
	}
}
