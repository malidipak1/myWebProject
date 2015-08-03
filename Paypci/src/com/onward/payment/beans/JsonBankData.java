/**
 *
 */
package com.onward.payment.beans;

import com.appsdaily.payment.scm.constants.Constants;
import com.appsdaily.util.LogUtils;
import com.onward.payment.helpers.URLConnectionCCAvenue;
import com.onward.scm.utility.DateUtils;

/**
 * @author vinod.talapa
 *
 */
public class JsonBankData {

	private static String jsonData="processData([{"+"OPTNBK"+":"+"[{\"cardSelectionId\":\"860\",\"cardName\":\"Indusind Bank\",\"cardType\":\"NBK\","
			+ "\"payOptType\":\"OPTNBK\",\"dataAcceptedAt\":\"Service Provider\",\"status\":\"ACTI\",\"statusMessage\":\"\"},"
			+ "{\"cardSelectionId\":\"240\",\"cardName\":\"Bank of India\",\"cardType\":\"NBK\",\"payOptType\":\"OPTNBK\","
			+ "\"dataAcceptedAt\":\"Service Provider\",\"status\":\"ACTI\",\"statusMessage\":\"\"},{\"cardSelectionId\":\"570\","
			+ "\"cardName\":\"United Bank Of India\",\"cardType\":\"NBK\",\"payOptType\":\"OPTNBK\",\"dataAcceptedAt\":\"Service Provider\""
			+ ",\"status\":\"ACTI\",\"statusMessage\":\"\"},{\"cardSelectionId\":\"760\",\"cardName\":\"Karur Vysya Bank\","
			+ "\"cardType\":\"NBK\",\"payOptType\":\"OPTNBK\",\"dataAcceptedAt\":\"Service Provider\",\"status\":\"ACTI\","
			+ "\"statusMessage\":\"\"},{\"cardSelectionId\":\"330\",\"cardName\":\"Deutsche Bank\",\"cardType\":\"NBK\","
			+ "\"payOptType\":\"OPTNBK\",\"dataAcceptedAt\":\"Service Provider\",\"status\":\"ACTI\",\"statusMessage\":\"\"},"
			+ "{\"cardSelectionId\":\"450\",\"cardName\":\"Standard Chartered Bank\",\"cardType\":\"NBK\",\"payOptType\":\"OPTNBK\","
			+ "\"dataAcceptedAt\":\"Service Provider\",\"status\":\"ACTI\",\"statusMessage\":\"\"},{\"cardSelectionId\":\"310\","
			+ "\"cardName\":\"Bank of Baroda\",\"cardType\":\"NBK\",\"payOptType\":\"OPTNBK\",\"dataAcceptedAt\":\"Service Provider\","
			+ "\"status\":\"ACTI\",\"statusMessage\":\"\"},{\"cardSelectionId\":\"190\",\"cardName\":\"Union Bank of India\","
			+ "\"cardType\":\"NBK\",\"payOptType\":\"OPTNBK\",\"dataAcceptedAt\":\"Service Provider\",\"status\":\"ACTI\","
			+ "\"statusMessage\":\"\"},{\"cardSelectionId\":\"530\",\"cardName\":\"State Bank of India\",\"cardType\":\"NBK\","
			+ "\"payOptType\":\"OPTNBK\",\"dataAcceptedAt\":\"Service Provider\",\"status\":\"ACTI\",\"statusMessage\":\"\"},"
			+ "{\"cardSelectionId\":\"1130\",\"cardName\":\"Catholic Syrian Bank\",\"cardType\":\"NBK\",\"payOptType\":\"OPTNBK\","
			+ "\"dataAcceptedAt\":\"Service Provider\",\"status\":\"ACTI\",\"statusMessage\":\"\"},{\"cardSelectionId\":\"620\","
			+ "\"cardName\":\"Tamilnad Mercantile Bank\",\"cardType\":\"NBK\",\"payOptType\":\"OPTNBK\",\"dataAcceptedAt\":\"Service Provider\","
			+ "\"status\":\"ACTI\",\"statusMessage\":\"\"},{\"cardSelectionId\":\"740\",\"cardName\":\"Central Bank of India\",\"cardType\":\"NBK\","
			+ "\"payOptType\":\"OPTNBK\",\"dataAcceptedAt\":\"Service Provider\",\"status\":\"ACTI\",\"statusMessage\":\"\"},{\"cardSelectionId\":\"680\","
			+ "\"cardName\":\"State Bank of Travencore\",\"cardType\":\"NBK\",\"payOptType\":\"OPTNBK\",\"dataAcceptedAt\":\"Service Provider\","
			+ "\"status\":\"ACTI\",\"statusMessage\":\"\"},{\"cardSelectionId\":\"880\",\"cardName\":\"State Bank of Patiala\",\"cardType\":\"NBK\","
			+ "\"payOptType\":\"OPTNBK\",\"dataAcceptedAt\":\"Service Provider\",\"status\":\"ACTI\",\"statusMessage\":\"\"},{\"cardSelectionId\":\"130\","
			+ "\"cardName\":\"Yes Bank\",\"cardType\":\"NBK\",\"payOptType\":\"OPTNBK\",\"dataAcceptedAt\":\"Service Provider\",\"status\":\"ACTI\","
			+ "\"statusMessage\":\"\"},{\"cardSelectionId\":\"550\",\"cardName\":\"State Bank of Mysore\",\"cardType\":\"NBK\",\"payOptType\":\"OPTNBK\","
			+ "\"dataAcceptedAt\":\"Service Provider\",\"status\":\"ACTI\",\"statusMessage\":\"\"},{\"cardSelectionId\":\"830\",\"cardName\":\"ING Vysya Bank\","
			+ "\"cardType\":\"NBK\",\"payOptType\":\"OPTNBK\",\"dataAcceptedAt\":\"Service Provider\",\"status\":\"ACTI\",\"statusMessage\":\"\"},"
			+ "{\"cardSelectionId\":\"560\",\"cardName\":\"State Bank of Hyderabad\",\"cardType\":\"NBK\",\"payOptType\":\"OPTNBK\","
			+ "\"dataAcceptedAt\":\"Service Provider\",\"status\":\"ACTI\",\"statusMessage\":\"\"},{\"cardSelectionId\":\"300\","
			+ "\"cardName\":\"HDFC Bank Retail\",\"cardType\":\"NBK\",\"payOptType\":\"OPTNBK\",\"dataAcceptedAt\":\"Service Provider\","
			+ "\"status\":\"ACTI\",\"statusMessage\":\"\"},{\"cardSelectionId\":\"750\",\"cardName\":\"Bank of Maharashtra\",\"cardType\":\"NBK\","
			+ "\"payOptType\":\"OPTNBK\",\"dataAcceptedAt\":\"Service Provider\",\"status\":\"ACTI\",\"statusMessage\":\"\"},{\"cardSelectionId\":\"200\","
			+ "\"cardName\":\"Vijaya Bank\",\"cardType\":\"NBK\",\"payOptType\":\"OPTNBK\",\"dataAcceptedAt\":\"Service Provider\",\"status\":\"ACTI\","
			+ "\"statusMessage\":\"\"},{\"cardSelectionId\":\"930\",\"cardName\":\"Canara Bank\",\"cardType\":\"NBK\",\"payOptType\":\"OPTNBK\","
			+ "\"dataAcceptedAt\":\"Service Provider\",\"status\":\"ACTI\",\"statusMessage\":\"\"},{\"cardSelectionId\":\"370\",\"cardName\":\"Dhanlaxmi Bank\","
			+ "\"cardType\":\"NBK\",\"payOptType\":\"OPTNBK\",\"dataAcceptedAt\":\"Service Provider\",\"status\":\"ACTI\",\"statusMessage\":\"\"},"
			+ "{\"cardSelectionId\":\"950\",\"cardName\":\"State Bank Of Bikaner and Jaipur\",\"cardType\":\"NBK\",\"payOptType\":\"OPTNBK\","
			+ "\"dataAcceptedAt\":\"Service Provider\",\"status\":\"ACTI\",\"statusMessage\":\"\"},{\"cardSelectionId\":\"1220\","
			+ "\"cardName\":\"Punjab National Bank\",\"cardType\":\"NBK\",\"payOptType\":\"OPTNBK\",\"dataAcceptedAt\":\"Service Provider\","
			+ "\"status\":\"ACTI\",\"statusMessage\":\"\"},{\"cardSelectionId\":\"270\",\"cardName\":\"Federal Bank\",\"cardType\":\"NBK\","
			+ "\"payOptType\":\"OPTNBK\",\"dataAcceptedAt\":\"Service Provider\",\"status\":\"ACTI\",\"statusMessage\":\"\"},{\"cardSelectionId\":\"910\","
			+ "\"cardName\":\"Kotak Mahindra Bank\",\"cardType\":\"NBK\",\"payOptType\":\"OPTNBK\",\"dataAcceptedAt\":\"Service Provider\","
			+ "\"status\":\"ACTI\",\"statusMessage\":\"\"},{\"cardSelectionId\":\"350\",\"cardName\":\"J and K Bank\",\"cardType\":\"NBK\","
			+ "\"payOptType\":\"OPTNBK\",\"dataAcceptedAt\":\"Service Provider\",\"status\":\"ACTI\",\"statusMessage\":\"\"},"
			+ "{\"cardSelectionId\":\"540\",\"cardName\":\"DCB BANK Personal\",\"cardType\":\"NBK\",\"payOptType\":\"OPTNBK\","
			+ "\"dataAcceptedAt\":\"Service Provider\",\"status\":\"ACTI\",\"statusMessage\":\"\"},{\"cardSelectionId\":\"180\","
			+ "\"cardName\":\"South Indian Bank\",\"cardType\":\"NBK\",\"payOptType\":\"OPTNBK\",\"dataAcceptedAt\":\"Service Provider\","
			+ "\"status\":\"ACTI\",\"statusMessage\":\"\"},{\"cardSelectionId\":\"10\",\"cardName\":\"ICICI Bank\",\"cardType\":\"NBK\","
			+ "\"payOptType\":\"OPTNBK\",\"dataAcceptedAt\":\"Service Provider\",\"status\":\"ACTI\",\"statusMessage\":\"\"},"
			+ "{\"cardSelectionId\":\"440\",\"cardName\":\"City Union Bank\",\"cardType\":\"NBK\",\"payOptType\":\"OPTNBK\","
			+ "\"dataAcceptedAt\":\"Service Provider\",\"status\":\"ACTI\",\"statusMessage\":\"\"},{\"cardSelectionId\":\"340\","
			+ "\"cardName\":\"Bank of Bahrain and Kuwait\",\"cardType\":\"NBK\",\"payOptType\":\"OPTNBK\",\"dataAcceptedAt\":\"Service Provider\","
			+ "\"status\":\"ACTI\",\"statusMessage\":\"\"},{\"cardSelectionId\":\"520\",\"cardName\":\"IDBI Bank\",\"cardType\":\"NBK\","
			+ "\"payOptType\":\"OPTNBK\",\"dataAcceptedAt\":\"Service Provider\",\"status\":\"ACTI\",\"statusMessage\":\"\"},{\"cardSelectionId\":\"160\","
			+ "\"cardName\":\"Oriental Bank Of Commerce\",\"cardType\":\"NBK\",\"payOptType\":\"OPTNBK\",\"dataAcceptedAt\":\"Service Provider\","
			+ "\"status\":\"ACTI\",\"statusMessage\":\"\"},{\"cardSelectionId\":\"490\",\"cardName\":\"Indian Bank\",\"cardType\":\"NBK\","
			+ "\"payOptType\":\"OPTNBK\",\"dataAcceptedAt\":\"Service Provider\",\"status\":\"ACTI\",\"statusMessage\":\"\"},{\"cardSelectionId\":\"120\","
			+ "\"cardName\":\"Corporation Bank\",\"cardType\":\"NBK\",\"payOptType\":\"OPTNBK\",\"dataAcceptedAt\":\"Service Provider\",\"status\":\"ACTI\","
			+ "\"statusMessage\":\"\"},{\"cardSelectionId\":\"420\",\"cardName\":\"Indian Overseas NetBanking\",\"cardType\":\"NBK\",\"payOptType\":\"OPTNBK\","
			+ "\"dataAcceptedAt\":\"Service Provider\",\"status\":\"ACTI\",\"statusMessage\":\"\"},{\"cardSelectionId\":\"140\",\"cardName\":\"Karnataka Bank\","
			+ "\"cardType\":\"NBK\",\"payOptType\":\"OPTNBK\",\"dataAcceptedAt\":\"Service Provider\",\"status\":\"ACTI\",\"statusMessage\":\"\"},"
			+ "{\"cardSelectionId\":\"50\",\"cardName\":\"Axis Bank\",\"cardType\":\"NBK\",\"payOptType\":\"OPTNBK\",\"dataAcceptedAt\":\"Service Provider\","
			+ "\"status\":\"ACTI\",\"statusMessage\":\"\"}]"+","+"payOpt"+":"+"OPTNBK"+"},{"+"OPTCRDC"+":"+"[{\"cardSelectionId\":\"1510\","
			+ "\"cardName\":\"ICICI IVR\",\"cardType\":\"CRDC\",\"payOptType\":\"OPTCRDC\",\"dataAcceptedAt\":\"Paynimo\","
			+ "\"status\":\"ACTI\",\"statusMessage\":\"\"},{\"cardSelectionId\":\"1520\",\"cardName\":\"ICICI CRT MOTO\",\"cardType\":\"CRDC\","
			+ "\"payOptType\":\"OPTCRDC\",\"dataAcceptedAt\":\"Paynimo\",\"status\":\"ACTI\",\"statusMessage\":\"\"},{\"cardSelectionId\":\"1070\","
			+ "\"cardName\":\"amex\",\"cardType\":\"CRDC\",\"payOptType\":\"OPTCRDC\",\"dataAcceptedAt\":\"Paynimo\",\"status\":\"ACTI\",\"statusMessage\":\"\"},"
			+ "{\"cardSelectionId\":\"1060\",\"cardName\":\"VISA / MASTER / MAESTRO\",\"cardType\":\"CRDC\",\"payOptType\":\"OPTCRDC\","
			+ "\"dataAcceptedAt\":\"Paynimo\",\"status\":\"ACTI\",\"statusMessage\":\"\"}]"+","+"payOpt"+":"+"OPTCRDC"+"},{"+"OPTDBCRD"+":"+""
			+ "[{\"cardSelectionId\":\"1370\",\"cardName\":\"RuPay\",\"cardType\":\"DBCRD\",\"payOptType\":\"OPTDBCRD\",\"dataAcceptedAt\":\"Service Provider\","
			+ "\"status\":\"ACTI\",\"statusMessage\":\"\"},{\"cardSelectionId\":\"1170\",\"cardName\":\"VISA / MASTER / MAESTRO Card\",\"cardType\":\"DBCRD\","
			+ "\"payOptType\":\"OPTDBCRD\",\"dataAcceptedAt\":\"Service Provider\",\"status\":\"ACTI\",\"statusMessage\":\"\"},{\"cardSelectionId\":\"1530\","
			+ "\"cardName\":\"ICICI DBT MOTO\",\"cardType\":\"DBCRD\",\"payOptType\":\"OPTDBCRD\",\"dataAcceptedAt\":\"Service Provider\",\"status\":\"ACTI\","
			+ "\"statusMessage\":\"\"}]"+","+"payOpt"+":"+"OPTDBCRD"+"}])";

	private static long timestamp;

	static {
		update();
	}
	
	public JsonBankData() {
		//update();
	}

	/**
	 * @param response
	 * @param request
	 * @return the jsonDate
	 */
	public static String getJsonData() {
		long currentTime= DateUtils.getCurrentTimeInMilliSeconds();
		if((timestamp-currentTime)<0){
	        update();
		}
		return jsonData;
	}

	private static synchronized void update(){

		long currentTime= DateUtils.getCurrentTimeInMilliSeconds();
		LogUtils.info("[PciPaymentService][JsonBankData] Update Json Bank Data on"+currentTime);
		if((timestamp-currentTime)<0){
		    String strurl=Constants.PAYNIMOURL;
	        String responseStr = "failed";
	        try {
	            responseStr = URLConnectionCCAvenue.HttpURLConnectionrequest(strurl, true);
	            if(!("failed".equals(responseStr) || "".equals(responseStr))) {
	            	responseStr="processData("+responseStr+")";
					jsonData=responseStr;
				}
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        timestamp=DateUtils.getCurrentTimeInMilliSeconds()+Constants.MILLIINADAY;
		}
	}

/*		public static void main(String[] args) {
			String url="https://www.tekprocess.co.in/mobile/AppsDailyPM.jsp?RequestType=PM&MerchantIdentifier=T3239";
			String responseStr = URLConnectionCCAvenue.HttpURLConnectionrequest(url, true);
            if(!("failed".equals(responseStr) || "".equals(responseStr))) {
            	responseStr="processData("+responseStr+")";
            }
            System.out.println(responseStr);
		}*/



}
