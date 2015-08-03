package com.onward.payment.beans;

public abstract class PaymentDetail implements IPaymentDetail {

	private String cardName;
	private String cardNumber;
	private String cvvNumber;
	private String expiryMonth;
	private String expiryYear;
	
	private String amount;
	private String orderId;
	
	private String mobile;
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCvvNumber() {
		return cvvNumber;
	}
	public void setCvvNumber(String cvvNumber) {
		this.cvvNumber = cvvNumber;
	}
	public String getExpiryMonth() {
		return expiryMonth;
	}
	public void setExpiryMonth(String expiryMonth) {
		this.expiryMonth = expiryMonth;
	}
	public String getExpiryYear() {
		return expiryYear;
	}
	public void setExpiryYear(String expiryYear) {
		this.expiryYear = expiryYear;
	}
	
	@Override
	public String toString() {
		return "PaymentDetail [cardName=" + cardName + ", expiryMonth="
				+ expiryMonth + ", expiryYear=" + expiryYear + ", amount="
				+ amount + ", orderId=" + orderId + ", mobile=" + mobile + "]";
	}

	
	
}
