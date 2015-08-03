package com.appsdaily.payment.analytics;

public class AnalyticsActionLogDOBean {

    private long subscriberId;
    private long guest_id;
    private long accountId;
    private long partnerId;
    private long productId;
    private String appVersion;
    private String androidId;
    private String imei;

    private long actionId;
    private long timeStamp;
    private String ap1Value;
    private String ap2Value;
    private String ap3Value;
    private String ap4Value;
    private String ap5Value;

    private String gender;
    private String dob;
    private String location;
    private String email_id;
    private String mobile;
    private String gcm_id;

    private long retailer_id;
    private long sales_id;

    private long creation_date;
    private int age;
    private long promotion_id;

    public long getPromotion_id() {
		return promotion_id;
	}




	public void setPromotion_id(long promotion_id) {
		this.promotion_id = promotion_id;
	}




	/**
     * @return the age
     */
    public int getAge() {

        return age;
    }




    /**
     * @param age the age to set
     */
    public void setAge( int age ) {

        this.age = age;
    }



    /**
     * @return the creation_date
     */
    public long getCreation_date() {

        return creation_date;
    }



    /**
     * @param creation_date the creation_date to set
     */
    public void setCreation_date( long creation_date ) {

        this.creation_date = creation_date;
    }


    public long getAccountId() {
		return accountId;
	}


	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public long getPartnerId() {
		return partnerId;
	}


	public void setPartnerId(long partnerId) {
		this.partnerId = partnerId;
	}


	public long getProductId() {
		return productId;
	}


	public void setProductId(long productId) {
		this.productId = productId;
	}


	public String getAppVersion() {
		return appVersion;
	}


	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}


	public String getImei() {
		return imei;
	}


	public void setImei(String imei) {
		this.imei = imei;
	}


	public String getEmail_id() {
		return email_id;
	}


	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getGcm_id() {
		return gcm_id;
	}


	public void setGcm_id(String gcm_id) {
		this.gcm_id = gcm_id;
	}


	/**
     * @return the androidId
     */
    public String getAndroidId() {

        return androidId;
    }


    /**
     * @param androidId the androidId to set
     */
    public void setAndroidId( String androidId ) {

        this.androidId = androidId;
    }

    /**
     * @return the subscriberId
     */
    public long getSubscriberId() {

        return subscriberId;
    }

    /**
     * @param subscriberId the subscriberId to set
     */
    public void setSubscriberId( long subscriberId ) {

        this.subscriberId = subscriberId;
    }

    /**
     * @return the actionId
     */
    public long getActionId() {

        return actionId;
    }

    /**
     * @param actionId the actionId to set
     */
    public void setActionId( long actionId ) {

        this.actionId = actionId;
    }

    /**
     * @return the timeStamp
     */
    public long getTimeStamp() {

        return timeStamp;
    }

    /**
     * @param timeStamp the timeStamp to set
     */
    public void setTimeStamp( long timeStamp ) {

        this.timeStamp = timeStamp;
    }

    /**
     * @return the ap1Value
     */
    public String getAp1Value() {

        return ap1Value;
    }

    /**
     * @param ap1Value the ap1Value to set
     */
    public void setAp1Value( String ap1Value ) {

        this.ap1Value = ap1Value;
    }

    /**
     * @return the ap2Value
     */
    public String getAp2Value() {

        return ap2Value;
    }

    /**
     * @param ap2Value the ap2Value to set
     */
    public void setAp2Value( String ap2Value ) {

        this.ap2Value = ap2Value;
    }

    /**
     * @return the ap3Value
     */
    public String getAp3Value() {

        return ap3Value;
    }

    /**
     * @param ap3Value the ap3Value to set
     */
    public void setAp3Value( String ap3Value ) {

        this.ap3Value = ap3Value;
    }


    /**
     * @return the ap4Value
     */
    public String getAp4Value() {

        return ap4Value;
    }



    /**
     * @param ap4Value the ap4Value to set
     */
    public void setAp4Value( String ap4Value ) {

        this.ap4Value = ap4Value;
    }



    /**
     * @return the ap5Value
     */
    public String getAp5Value() {

        return ap5Value;
    }



    /**
     * @param ap5Value the ap5Value to set
     */
    public void setAp5Value( String ap5Value ) {

        this.ap5Value = ap5Value;
    }


    /**
     * @return the gender
     */
    public String getGender() {

        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender( String gender ) {

        this.gender = gender;
    }

    /**
     * @return the dob
     */
    public String getDob() {

        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob( String dob ) {

        this.dob = dob;
    }

    /**
     * @return the location
     */
    public String getLocation() {

        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation( String location ) {

        this.location = location;
    }


    /**
     * @return the sales_id
     */
    public long getSales_id() {
        return sales_id;
    }


    /**
     * @param sales_id the sales_id to set
     */
    public void setSales_id( long sales_id ) {
        this.sales_id = sales_id;
    }


    /**
     * @return the retailer_id
     */
    public long getRetailer_id() {
        return retailer_id;
    }


    /**
     * @param retailer_id the retailer_id to set
     */
    public void setRetailer_id( long retailer_id ) {
        this.retailer_id = retailer_id;
    }


    /**
     * @return the guest_id
     */
    public long getGuest_id() {

        return guest_id;
    }


    /**
     * @param guest_id the guest_id to set
     */
    public void setGuest_id( long guest_id ) {

        this.guest_id = guest_id;
    }
}
