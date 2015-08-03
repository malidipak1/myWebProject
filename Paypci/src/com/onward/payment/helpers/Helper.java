package com.onward.payment.helpers;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;

import com.appsdaily.payment.scm.constants.Constants;
import com.ccavenue.security.AesCryptUtil;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Apr 9, 2009
 * Time: 7:03:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class Helper {
   public static String nullToBlank(String str) {
        if (str == null) {
            str = "";
        }
        return str;
   }

    public static String getCurrentTimeStamp() {

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        String yearStr = String.valueOf(year);

        int month = cal.get(Calendar.MONTH) + 1;
        String monthStr = null;
        if (month < 10) {
            monthStr = "0" + String.valueOf(month);
        } else {
            monthStr = String.valueOf(month);
        }

        int day = cal.get(Calendar.DAY_OF_MONTH);
        String dayStr = null;
        if (day < 10) {
            dayStr = "0" + String.valueOf(day);
        } else {
            dayStr = String.valueOf(day);
        }

        int hour = cal.get(Calendar.HOUR_OF_DAY);
        String hourStr = null;
        if (hour < 10) {
            hourStr = "0" + String.valueOf(hour);
        } else {
            hourStr = String.valueOf(hour);
        }

        int minute = cal.get(Calendar.MINUTE);
        String minuteStr = null;
        if (minute < 10) {
            minuteStr = "0" + String.valueOf(minute);
        } else {
            minuteStr = String.valueOf(minute);
        }

        int second = cal.get(Calendar.SECOND);
        String secondStr = null;
        if (second < 10) {
            secondStr = "0" + String.valueOf(second);
        } else {
            secondStr = String.valueOf(second);
        }

        String timeStamp = yearStr + monthStr + dayStr + hourStr + minuteStr + secondStr;

        return timeStamp;
    }

   public static boolean containsAllDigits (String input)
   {
	   	Pattern p = Pattern.compile("[\\d]*");
		Matcher m = p.matcher(input);
		boolean b = m.matches();
	    return b;
   }
   
	public static boolean isBlankOrNull(String value) {
		return ( value == null ) || ( value.trim().length() == 0 );
	}
	
	public static String randomPasswordGenerator(){
		String ALPHA_CAPS  = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    String ALPHA   = "abcdefghijklmnopqrstuvwxyz";
	    String NUM     = "0123456789";
	    String SPL_CHARS   = "!@#$%^&*_=+-/";
	    int minLen = 5;
	    int maxLen = 5;
	    int noOfCAPSAlpha = 1;
        int noOfDigits = 1;
        int noOfSplChars = 0;
	    
	    Random rnd = new Random();
        int len = rnd.nextInt(( maxLen - minLen ) + 1) + minLen;
        char[] pswd = new char[len];
        int index = 0;
        for (int i = 0; i < noOfCAPSAlpha; i++) {
            index = getNextIndex(rnd, len, pswd);
            pswd[index] = ALPHA_CAPS.charAt(rnd.nextInt(ALPHA_CAPS.length()));
        }
        for (int i = 0; i < noOfDigits; i++) {
            index = getNextIndex(rnd, len, pswd);
            pswd[index] = NUM.charAt(rnd.nextInt(NUM.length()));
        }
        for (int i = 0; i < noOfSplChars; i++) {
            index = getNextIndex(rnd, len, pswd);
            pswd[index] = SPL_CHARS.charAt(rnd.nextInt(SPL_CHARS.length()));
        }
        for(int i = 0; i < len; i++) {
            if(pswd[i] == 0) {
                pswd[i] = ALPHA.charAt(rnd.nextInt(ALPHA.length()));
            }
        }
        return new String(pswd);
		
	}
	
	private static int getNextIndex(Random rnd, int len, char[] pswd) {
        int index = rnd.nextInt(len);
        while(pswd[index = rnd.nextInt(len)] != 0) {
            ;
        }
        return index;
    }
	
	
	
	public static String getClientIpAddr(HttpServletRequest request) {  
	    String ip = request.getHeader("X-Forwarded-For");  
	    if (( ip == null ) || ( ip.length() == 0 ) || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("Proxy-Client-IP");  
	    }  
	    if (( ip == null ) || ( ip.length() == 0 ) || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("WL-Proxy-Client-IP");  
	    }  
	    if (( ip == null ) || ( ip.length() == 0 ) || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("HTTP_CLIENT_IP");  
	    }  
	    if (( ip == null ) || ( ip.length() == 0 ) || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
	    }  
	    if (( ip == null ) || ( ip.length() == 0 ) || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getRemoteAddr();  
	    }
	    if(ip == null){
	    	ip = "127.0.0.1";
	    }
	    return ip;  
	}
	
	public static String HttpURLConnectionrequest(String strurl,String uploadString, String referer, boolean isSSL) {
		//strurl = URLEncoder.encode(strurl);
		String response = null;
		//System.out.println("strurl= "+strurl+" uploadString = "+uploadString);
		if (strurl!=null) {
			///////////////
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
			}
			//////////////

			HttpURLConnection httpurlConnection = null;//s
			try {
				URL url = new URL(strurl);
				httpurlConnection = (HttpURLConnection) url.openConnection();//s
				httpurlConnection.setConnectTimeout(20000);
				httpurlConnection.setReadTimeout(60000);
				httpurlConnection.setDoInput(true);
				if(referer != null){
					httpurlConnection.setRequestProperty("Referer", referer);
				}
				if (uploadString==null) {
					httpurlConnection.setDoOutput(false);
					httpurlConnection.setRequestMethod("GET");
					httpurlConnection.setRequestProperty("Content-Type","text/html; charset=UTF-8");
				}else {
					httpurlConnection.setDoOutput(true);
					httpurlConnection.setRequestMethod("POST");
					httpurlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
					httpurlConnection.setRequestProperty("Content-Length", String.valueOf(uploadString.length()));
				}
				httpurlConnection.connect();
				if (uploadString!=null) {
					OutputStream outputStream=httpurlConnection.getOutputStream();
					outputStream.write(uploadString.getBytes());
					outputStream.close();
				}
				int respcode = httpurlConnection.getResponseCode();
				//System.out.println("respcode = "+respcode);
				if (HttpURLConnection.HTTP_OK == respcode) {
					//int totlen=httpurlConnection.getContentLength();
					InputStream httpis = httpurlConnection.getInputStream();
					// Wrap a BufferedReader around the InputStream
					BufferedReader rd = new BufferedReader(new InputStreamReader(httpis));
					String line = "";
					StringBuilder total = new StringBuilder();

					// Read response until the end
					while ((line = rd.readLine()) != null) { 
						total.append(line); 
					}

					response = total.toString();

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (httpurlConnection != null) {
				httpurlConnection.disconnect();
			}	
		}
		//System.out.println("response = "+response);
		return response;
	}
	
	public static String base64Encode(String token) {
		AesCryptUtil aesUtil=new AesCryptUtil(Constants.ENC_KEY_CCEVENUE);
		String encRequest = aesUtil.encrypt(token);
	    return encRequest;
	}


	public static String base64Decode(String token) {
		AesCryptUtil aesUtil=new AesCryptUtil(Constants.ENC_KEY_CCEVENUE);
		String decrRequest = aesUtil.decrypt(token);
	    return decrRequest;
	}
	
	private static void TrustManager() throws Exception {
		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			@Override
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			@Override
			public void checkClientTrusted(X509Certificate[] certs, String authType) {
			}

			@Override
			public void checkServerTrusted(X509Certificate[] certs, String authType) {
			}
		} };

		// Install the all-trusting trust manager
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

		// Create all-trusting host name verifier
		HostnameVerifier allHostsValid = new HostnameVerifier() {
			@Override
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};

		// Install the all-trusting host verifier
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

	}

	public static String urlEncode(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			switch (s.charAt(i)) {
			case ' ':
				sb.append("%20");
				break;
			case '+':
				sb.append("%2b");
				break;
			case '\'':
				sb.append("%27");
				break;
			case '<':
				sb.append("%3c");
				break;
			case '>':
				sb.append("%3e");
				break;
			case '#':
				sb.append("%23");
				break;
			case '%':
				sb.append("%25");
				break;
			case '{':
				sb.append("%7b");
				break;
			case '}':
				sb.append("%7d");
				break;
			case '\\':
				sb.append("%5c");
				break;
			case '^':
				sb.append("%5e");
				break;
			case '~':
				sb.append("%73");
				break;
			case '[':
				sb.append("%5b");
				break;
			case ']':
				sb.append("%5d");
				break;
			default:
				sb.append(s.charAt(i));
				break;
			}
		}
		return sb.toString();
	}

	public static String dateFormatString(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		java.util.Date myDate = new java.util.Date();
		String result = dateFormat.format(myDate);

		return result;
	}
		
	public static String decimalFormatString (double db) {
		DecimalFormat df = new DecimalFormat("#.00"); 
		return df.format(db); 
	}
	public static String decimalFormatString (float db) {
		return decimalFormatString(new Double(new Float(db).toString())); 
	}
	public static String decimalFormatString (String str) {
		double db = 0;
		try {
			db = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			db = 0;
		}
		
		return decimalFormatString(db); 
	}
	
	public static String sendPost(String urlParameters, String urlStr, boolean isssl) throws Exception {
		System.out.println("url : " + urlStr);
		if (isssl) {
			TrustManager();
		}
		URL obj = new URL(urlStr);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
		// add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("Content-Type", "application/json");

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + urlStr);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());
		return response.toString();
	}

	

	public static void main(String[] args) {
		/*String urlStr = "https://www.appsdaily.info/api/rs/analytics";
		String order_id = "0";

		String urlParameters = "{\"analytics\":[{\"timestamp\":\"" + 0 + "\",\"order_id\":\"" + order_id + "\",\"card_type\":\"" + "creditCard"
				+ "\",\"bank_name\":\"debit_card\",\"action_id\":\"1353\"}],\"subinfo\":{},\"validation_info\":{}}";

		try {
			Helper.sendPost(urlParameters, urlStr, false);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		try {
			System.out.println(SHACheckSum("gtKFFx|CV010010|100.00|DailySecurityMR|dipak|test@gmest.com|TestParam1||||||||||eCwWELxi"));
			System.out.println(SHACheckSum("C0Dr8m|12345|10|Shopping|Test|test@test.com||abc||15|||||||3sf0jURk"));
			System.out.println(DigestUtils.sha512Hex("C0Dr8m|12345|10|Shopping|Test|test@test.com||abc||15|||||||3sf0jURk"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String SHACheckSum(String str) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-512");

		md.update(str.getBytes());
		byte[] mdbytes = md.digest();
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < mdbytes.length; i++)
			hexString.append(Integer.toHexString(0xFF & mdbytes[i]));

		return hexString.toString();
	}

}
