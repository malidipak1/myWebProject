/**
 * 
 */
package com.onward.payment.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author vinod.talapa
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class PaymentHelperBean {
	@JsonProperty(value="cancel_url")
	private String cancelURL;
	
	@JsonProperty(value="redirect_url")
	private String redirectURL;
	
	private String amount;
	private String order_id;
	private String group;
	
	private long subscriber_id;
	private long guest_id;
	private long partner_id;
	private long product_id;
	
	private String merchant_param1;
	private String merchant_param2;
	private String merchant_param3;
	private String merchant_param4;
	private String merchant_param5;
	private String merchant_param6;
	private String pay_sub_type;
	private String info;
	private String bank;
	
	public String getMerchant_param1() {
		return merchant_param1;
	}
	public void setMerchant_param1(String merchant_param1) {
		this.merchant_param1 = merchant_param1;
	}
	public String getMerchant_param2() {
		return merchant_param2;
	}
	public void setMerchant_param2(String merchant_param2) {
		this.merchant_param2 = merchant_param2;
	}
	public String getMerchant_param3() {
		return merchant_param3;
	}
	public void setMerchant_param3(String merchant_param3) {
		this.merchant_param3 = merchant_param3;
	}
	public String getMerchant_param4() {
		return merchant_param4;
	}
	public void setMerchant_param4(String merchant_param4) {
		this.merchant_param4 = merchant_param4;
	}
	public String getMerchant_param5() {
		return merchant_param5;
	}
	public void setMerchant_param5(String merchant_param5) {
		this.merchant_param5 = merchant_param5;
	}
	public String getMerchant_param6() {
		return merchant_param6;
	}
	public void setMerchant_param6(String merchant_param6) {
		this.merchant_param6 = merchant_param6;
	}
	public String getPay_sub_type() {
		return pay_sub_type;
	}
	public void setPay_sub_type(String pay_sub_type) {
		this.pay_sub_type = pay_sub_type;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getCancelURL() {
		return cancelURL;
	}
	public void setCancelURL(String cancelURL) {
		this.cancelURL = cancelURL;
	}
	public String getRedirectURL() {
		return redirectURL;
	}
	public void setRedirectURL(String redirectURL) {
		this.redirectURL = redirectURL;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public long getSubscriber_id() {
		return subscriber_id;
	}
	public void setSubscriber_id(long subscriber_id) {
		this.subscriber_id = subscriber_id;
	}
	public long getGuest_id() {
		return guest_id;
	}
	public void setGuest_id(long guest_id) {
		this.guest_id = guest_id;
	}
	public long getPartner_id() {
		return partner_id;
	}
	public void setPartner_id(long partner_id) {
		this.partner_id = partner_id;
	}
	public long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}
}
