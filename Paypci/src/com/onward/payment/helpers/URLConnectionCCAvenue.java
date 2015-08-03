package com.onward.payment.helpers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.appsdaily.payment.util.PaymentProperties;

public class URLConnectionCCAvenue {

	public static String HttpURLConnectionrequest(String strurl, boolean isSSL){
		String urlString = "";
		String current=null;
		try {
	         BufferedReader in=null;
	         HttpURLConnection connection = null;
	         URL url=null;
	         if(isSSL){
					// Create a trust manager that does not validate certificate chains
					TrustManager[] trustAllCerts = new TrustManager[]{
					    new X509TrustManager() {
					        @Override
                            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					            return null;
					        }
					        @Override
                            public void checkClientTrusted(
					            java.security.cert.X509Certificate[] certs, String authType) {
					        }
					        @Override
                            public void checkServerTrusted(
					            java.security.cert.X509Certificate[] certs, String authType) {
					        }
					    }
					};

					// Install the all-trusting trust manager
					try {
					    SSLContext sc = SSLContext.getInstance("SSL");
					    sc.init(null, trustAllCerts, new java.security.SecureRandom());
					    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
					} catch (Exception e) {
					}
					try {
					    //System.setProperty("https.protocols", "TLSv1");
					   // System.out.println("https.protocols");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
	         //System.out.println(strurl);
	         url = new URL(strurl);
	         URLConnection urlConnection = url.openConnection();

	         if(urlConnection instanceof HttpURLConnection){
	        	 urlConnection.setRequestProperty("Referer",PaymentProperties.getAMAZON_DOMAIN());
	        	 connection = (HttpURLConnection) urlConnection;

	         }else {
	            urlString =  "failed";
	         }
	         if(connection!=null){
	             in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	             while((current = in.readLine()) != null){
	                urlString += current;
	             }
	         }else {
	                urlString =  "failed";
	             }
	    }catch (Exception e) {
	    	  e.printStackTrace();
	    }
		return urlString;
	}

}
