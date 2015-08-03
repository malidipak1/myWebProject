package com.appsdaily.payment.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.appsdaily.payment.error.PciPaymentErrorMsg;
import com.appsdaily.util.LogUtils;
import com.onward.scm.utility.CommonUtils;

public class PaymentProperties {
	//private static final Logger logger = LogManager.getLogger(PaymentProperties.class);

	private static final String FILE_PATH = "properties.payment";
	private static final String ENV = System.getProperty("ENV");
	private static ResourceBundle properties = null;

	private PaymentProperties() {
	}

	static {
		synchronized (PaymentProperties.class) {
			if (properties == null) {
				try {
					properties = ResourceBundle.getBundle(FILE_PATH + "."
							+ (CommonUtils.isNullOrEmpty(ENV) ? "prod" : ENV));
				} catch (MissingResourceException e) {
					LogUtils.error(PciPaymentErrorMsg.SYSTEM_ERROR_PROPERTY_FILE_NOT_FOUND, e);
				}
			}
		}
	}

	private static ResourceBundle getProperties() {
		return properties;
	}

	public static String getPropertyValue(String property) {
		return getProperties() != null ? getProperties().getString(property) : null;
	}

    public static final String AMAZON_DOMAIN="base_url";
    public static final String PROJECT_URL="project_url";
    public static final String PAYMENT_URL="payment_url";
    public static final String ANALYTICS_URL="analytics_url";
    public static final String ENC_KEY_CCEVENUE ="enc_key";
    public static final String KEY_TECH_PROCESS = "key_tech_process";
    public static final String IV_TECH_PROCESS = "iv_tech_process";
    public static final String MERCHANT_CODE_TP = "merchant_code_tp";
    private static final String ACCESS_CODE_CCEVENUE="access_code_ccevenue";
    
    public static String getAccessCodeCcevenue() {
		return getPropertyValue(ACCESS_CODE_CCEVENUE);
	}
    
	public static String getAMAZON_DOMAIN() {
		return getPropertyValue(AMAZON_DOMAIN);
	}

	public static String getProject_Url(){
	    return getPropertyValue(PROJECT_URL);
	}
	public static String getPaymentUrl(){
	    return getPropertyValue( PAYMENT_URL );
	}

	public static String getAnalyticsUrl(){
	    return getPropertyValue( ANALYTICS_URL );
	}


    /**
     * @return the encKeyCcevenue
     */
    public static String getEncKeyCcevenue() {

        return getPropertyValue(ENC_KEY_CCEVENUE);
    }


    /**
     * @return the keyTechProcess
     */
    public static String getKeyTechProcess() {

        return getPropertyValue(KEY_TECH_PROCESS);
    }


    /**
     * @return the ivTechProcess
     */
    public static String getIvTechProcess() {

        return getPropertyValue(IV_TECH_PROCESS);
    }


    /**
     * @return the merchantCodeTp
     */
    public static String getMerchantCodeTp() {

        return getPropertyValue(MERCHANT_CODE_TP);
    }
}
