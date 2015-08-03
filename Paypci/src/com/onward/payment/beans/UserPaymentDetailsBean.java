package com.onward.payment.beans;

public class UserPaymentDetailsBean extends PaymentDetail{
	

	private String paymentOption;
	//private String cardType;
	private String dataAccept;

	private String issuingBank;

	private boolean isError = false;
	private String errorMsg;
	
	private long subId;
	private long guestId;
	private long product_id;
	private long partner_id;
	
	private String card_category;
	
	public String getCard_category() {
		return card_category;
	}
	public void setCard_category(String card_category) {
		this.card_category = card_category;
	}
	public long getSubId() {
		return subId;
	}
	public void setSubId(long subId) {
		this.subId = subId;
	}
	public long getGuestId() {
		return guestId;
	}
	public void setGuestId(long guestId) {
		this.guestId = guestId;
	}
	public long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}
	public long getPartner_id() {
		return partner_id;
	}
	public void setPartner_id(long partner_id) {
		this.partner_id = partner_id;
	}
	private boolean isSave;
	
	public boolean isSave() {
		return isSave;
	}
	public void setSave(boolean isSave) {
		this.isSave = isSave;
	}

	public String getPaymentOption() {
		return paymentOption;
	}
	public void setPaymentOption(String paymentOption) {
		this.paymentOption = paymentOption;
	}
	/*public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}*/
	public String getDataAccept() {
		return dataAccept;
	}
	public void setDataAccept(String dataAccept) {
		this.dataAccept = dataAccept;
	}
	
	public String getIssuingBank() {
		return issuingBank;
	}
	public void setIssuingBank(String issuingBank) {
		this.issuingBank = issuingBank;
	}

	public boolean isError() {
		return isError;
	}
	public void setError(boolean isError) {
		this.isError = isError;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	@Override
	public String toString() {
		return "UserPaymentDetailsBean [paymentOption=" + paymentOption
				+ ", dataAccept=" + dataAccept + ", issuingBank=" + issuingBank
				+ ", isError=" + isError + ", errorMsg=" + errorMsg
				+ ", subId=" + subId + ", guestId=" + guestId + ", product_id="
				+ product_id + ", partner_id=" + partner_id
				+ ", card_category=" + card_category + ", isSave=" + isSave
				+ ", getMobile()=" + getMobile() + ", getAmount()="
				+ getAmount() + ", getOrderId()=" + getOrderId()
				+ ", getCardName()=" + getCardName() + ", getCardNumber()="
				+ getCardNumber() + ", getCvvNumber()=" + getCvvNumber()
				+ ", getExpiryMonth()=" + getExpiryMonth()
				+ ", getExpiryYear()=" + getExpiryYear() + ", hashCode()="
				+ hashCode() + "]";
	}
	


}
