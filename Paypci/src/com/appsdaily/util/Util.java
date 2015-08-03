package com.appsdaily.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

//import org.apache.commons.lang3.StringUtils;


public class Util {

    public static boolean isEmpty(Object sSource) {
        boolean returnValue = true;
        if (sSource instanceof String) {
            returnValue = isEmpty((String) sSource);
        } else if (sSource instanceof Collection) {
        	if (null != sSource && ((Collection<?>) sSource).size() > 0) {
                returnValue = false;
            }
        } else if (sSource instanceof Map) {
            if (null != sSource && ((Map<?, ?>) sSource).size() > 0) {
                returnValue = false;
            }
        } else if (sSource != null) {
            return false;
        }
        return returnValue;
    }

    public static Integer getParamIntValue(HttpServletRequest request, String paramName) {
    	String paramValue = request.getParameter(paramName);
    	if (paramValue == null) {
    		return null;
    	} else {
			return Integer.valueOf(paramValue);
    	}
    }
    
    public static Long getParamLongValue(HttpServletRequest request, String paramName) {
    	String paramValue = request.getParameter(paramName);
    	if (paramValue == null) {
    		return null;
    	} else {
			return Long.valueOf(paramValue);
    	}
    }

    public static boolean isEmpty(String sSource) {
        if (null == sSource || "".equals(sSource.trim()) || "null".equalsIgnoreCase(sSource)) {
            return true;
        }
        return false;
    }

    public static boolean doesUrlExists(String urlString) throws MalformedURLException, IOException {
        URL u = new URL(urlString);
        HttpURLConnection huc =  (HttpURLConnection)  u.openConnection();
        huc.setRequestMethod("GET");
        huc.connect();
        return huc.getResponseCode() == 200;
    }

   /* public static String toXmlString(Object obj) {
    	String strObj = String.valueOf(obj);
    	String defaultStr = StringUtils.defaultString(strObj);
    	if("null".equalsIgnoreCase(defaultStr)){
    		defaultStr = "";
    	}
    	return defaultStr;
    }*/
    
    public static BigDecimal toBigDecimalValue(Number number) {
        if (number == null) {
            return null;
        }

        if (number instanceof BigDecimal) {
            return (BigDecimal) number;
        } else {
            return new BigDecimal(number.doubleValue());
        }
    }


    public static void printDbRecords(List<Map<String, Object>> dbRows) {
    	LogUtils.logInfo("-------------------------------------------");
    	List<String> columnNames = new ArrayList<String>();
    	for (Map<String, Object> dbRow: dbRows) {
    		StringBuilder log = new StringBuilder();
    		if (columnNames.isEmpty()) {
    			columnNames.addAll(dbRow.keySet());
        		for (String columnName: columnNames) {
        			log.append(columnName).append("\t");
        		}
        		LogUtils.logInfo(log.toString());
        		log = new StringBuilder();
    		}
    		for (String columnName: columnNames) {
    			log.append(dbRow.get(columnName)).append("\t");
    		}
    		LogUtils.logInfo(log.toString());
    	}
    	LogUtils.logInfo("-------------------------------------------");
    }

    /**
     * Constructs a flat XML from a simple map.
     */
    public static String getXmlFromMap(Map<String, Object> map, String enclosingTag) {
    	StringBuilder builder = new StringBuilder();
    	if (!Util.isEmpty(enclosingTag)) {
    		builder.append("<").append(enclosingTag).append(">");
    	}
    	for (Map.Entry<String, Object> entry: map.entrySet()) {
    		
    		builder.append("<").append(entry.getKey()).append(">");
    		if(Util.isArray(entry.getValue())) {
    			
    			String []arrStr = (String [])entry.getValue();
    			for(String str : arrStr) {
	    			builder.append("<value><![CDATA[").append(str).append("]]></value>");
    			}	
    		} else {
	    		builder.append("<![CDATA[").append(entry.getValue()).append("]]>");
    		}
    		builder.append("</").append(entry.getKey()).append(">");
    	}
    	if (!Util.isEmpty(enclosingTag)) {
    		builder.append("</").append(enclosingTag).append(">");
    	}
    	return builder.toString();
    }

	public static void logMemoryUsage() {
    	double memory = Runtime.getRuntime().totalMemory() / (1024L * 1024L);
    	double freememory = Runtime.getRuntime().freeMemory() / (1024L * 1024L);
    	LogUtils.logInfo("memory: " + memory + ", freememory: " + freememory + ", percent_used: " + (memory-freememory)/memory);
	}    

	public static double getPercentMemoryUsed() {
    	double memory = Runtime.getRuntime().totalMemory() / (1024L * 1024L);
    	double freememory = Runtime.getRuntime().freeMemory() / (1024L * 1024L);
    	return (memory-freememory)/memory;
	}    

	public static boolean isArray(final Object obj) {
	    return obj instanceof Object[] || obj instanceof boolean[] ||
	       obj instanceof byte[] || obj instanceof short[] ||
	       obj instanceof char[] || obj instanceof int[] ||
	       obj instanceof long[] || obj instanceof float[] ||
	       obj instanceof double[];
	}
	
}