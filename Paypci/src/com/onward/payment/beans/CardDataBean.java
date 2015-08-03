package com.onward.payment.beans;

public class CardDataBean {
	private String	card_token;
	private long	subscriber_id;
	private long	guest_id;
	
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
	private long	partner_id;
	private long	product_id;
	private String	card_type;
	private String	card_number;
	private String	expiry_month;
	private String	expiry_year;
	private String	hash_value;
	private String card_category;
	
	public String getCard_category() {
		return card_category;
	}
	public void setCard_category(String card_category) {
		this.card_category = card_category;
	}
	private String maskeddigits;
	
	
	public String getMaskeddigits() {
		return maskeddigits;
	}
	public void setMaskeddigits(String maskeddigits) {
		this.maskeddigits = maskeddigits;
	}
	public String getCard_token() {
		return card_token;
	}
	public void setCard_token(String card_token) {
		this.card_token = card_token;
	}
	
	public String getCard_type() {
		return card_type;
	}
	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}

	public String getCard_number() {
		return card_number;
	}
	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}
	public String getExpiry_month() {
		return expiry_month;
	}
	public void setExpiry_month(String expiry_month) {
		this.expiry_month = expiry_month;
	}
	public String getExpiry_year() {
		return expiry_year;
	}
	public void setExpiry_year(String expiry_year) {
		this.expiry_year = expiry_year;
	}
	
	public String getHash_value() {
		return hash_value;
	}
	public void setHash_value(String hash_value) {
		this.hash_value = hash_value;
	}

}
