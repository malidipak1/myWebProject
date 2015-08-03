package com.onward.payment.beans;

public class MaskedCardBean {
	private String maskedcard;
	private String card_type;
	private String card_token;
	private String card_category;
	public String getCard_category() {
		return card_category;
	}
	public void setCard_category(String card_category) {
		this.card_category = card_category;
	}
	public String getMaskedcard() {
		return maskedcard;
	}
	public void setMaskedcard(String maskedcard) {
		this.maskedcard = maskedcard;
	}
	public String getCard_type() {
		return card_type;
	}
	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}
	public String getCard_token() {
		return card_token;
	}
	public void setCard_token(String card_token) {
		this.card_token = card_token;
	}

}
