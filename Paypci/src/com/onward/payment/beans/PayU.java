package com.onward.payment.beans;

import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import com.appsdaily.payment.scm.constants.Constants;
import com.appsdaily.payment.scm.constants.Constants.PaymentConfig;
import com.appsdaily.util.LogUtils;
import com.onward.payment.helpers.Helper;


public class PayU extends PaymentDetail{

	public enum PG {
		NETBANKING("NB"), CREDITCARD("CC"), DEBITCARD("DC"), EMI("EMI"), CASH("CASH");
		
		String payType;
		
		private PG(String txt) {
			this.payType = txt;
		}
		public String getValue() {
			return this.payType;
		}
	}
	
	private String key;
	private String firstName ;
	private String lastName;
	
	private String sUrl;
	private String fUrl;
	
	private String pg;
	private String email;
	
	private String prodInfo;
	 
	private String hash;
	
	private String udf1;
	private String udf2;
	private String udf3;
	private String udf4;
	private String udf5;
	 /**
	  * for net banking 
	  */
	private String bankCode;
	
	public void generateHash() {
		//sha512(key|txnid|amount|productinfo|firstname|email|udf1|udf2|udf3|udf4|udf5||||||SALT)
		StringBuffer hashStr = new StringBuffer();
		hashStr.append(this.getKey()).append("|");
		hashStr.append(this.getOrderId()).append("|");
		hashStr.append(Helper.decimalFormatString(this.getAmount())).append("|");
		hashStr.append(this.getProdInfo()).append("|");
		hashStr.append(this.getFirstName()).append("|");
		hashStr.append(this.getEmail()).append("|");
		hashStr.append(this.getUdf1()).append("|");
		hashStr.append(this.getUdf2()).append("|");
		hashStr.append(this.getUdf3()).append("|");
		hashStr.append(this.getUdf4()).append("|");
		hashStr.append(this.getUdf5()).append("|").append("|||||");
		hashStr.append(Constants.PAYU_HASH_SALT);

		LogUtils.info("String-to-Hash: " + hashStr.toString());
		
		try {
			
			this.setHash(DigestUtils.sha512Hex(hashStr.toString()));
			//this.setHash(Helper.SHACheckSum(hashStr.toString()));//not working out
			LogUtils.info("Hash-Value: " + this.getHash());
		} catch (Exception e) {
			LogUtils.error("Error while using DigestUtils.sha512Hex() ", e);
		} 
		
	}
	
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getFirstName() {	
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getsUrl() {
		return sUrl;
	}
	public void setsUrl(String sUrl) {
		this.sUrl = sUrl;
	}
	public String getfUrl() {
		return fUrl;
	}
	public void setfUrl(String fUrl) {
		this.fUrl = fUrl;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProdInfo() {
		return prodInfo;
	}
	public void setProdInfo(String prodInfo) {
		this.prodInfo = prodInfo;
	}

	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getUdf1() {
		return (udf1 == null) ? "" : udf1;
	}
	public void setUdf1(String udf1) {
		this.udf1 = udf1;
	}
	public String getUdf2() {
		return (udf2 == null) ? "" : udf2;
	}
	public void setUdf2(String udf2) {
		this.udf2 = udf2;
	}
	public String getUdf3() {
		return (udf3 == null) ? "" : udf3;
	}
	public void setUdf3(String udf3) {
		this.udf3 = udf3;
	}
	public String getUdf4() {
		return (udf4 == null) ? "" : udf4;	
	}
	public void setUdf4(String udf4) {
		this.udf4 = udf4;
	}
	public String getUdf5() {
		return (udf5 == null) ? "" : udf5;		
	}
	public void setUdf5(String udf5) {
		this.udf5 = udf5;
	}

	public String getBankCode() {
		return (bankCode == null) ? "" : bankCode;
	}


	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}


	@Override
	public String toString() {
		return "PayU [key=" + key + ", firstName=" + firstName + ", lastName="
				+ lastName + ", sUrl=" + sUrl + ", fUrl=" + fUrl + ", email="
				+ email + ", prodInfo=" + prodInfo + ", hash=" + hash
				+ ", udf1=" + udf1 + ", udf2=" + udf2 + ", udf3=" + udf3
				+ ", udf4=" + udf4 + ", udf5=" + udf5 + ", bankCode="
				+ bankCode + ", getMobile()=" + getMobile() + ", getAmount()="
				+ getAmount() + ", getOrderId()=" + getOrderId()
				+ ", getCardName()=" + getCardName() + ", getCardNumber()="
				+ getCardNumber() + ", getCvvNumber()=" + getCvvNumber()
				+ ", getExpiryMonth()=" + getExpiryMonth()
				+ ", getExpiryYear()=" + getExpiryYear() + ", hashCode()="
				+ hashCode() + "]";
	}


	public String getPg() {
		return pg;
	}


	public void setPg(String pg) {
		this.pg = pg;
	}
	
	
	
}