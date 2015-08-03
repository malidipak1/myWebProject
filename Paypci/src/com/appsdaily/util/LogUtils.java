package com.appsdaily.util;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;



public class LogUtils {

	private static final Logger logger = Logger.getLogger(LogUtils.class);
	
    public static void logInfo(String msg) {
        // logger.info(msg);
        logger.log(LogUtils.class.getCanonicalName(), Level.INFO, msg, null);
    }
    
    public static void logWarn(String msg) {
        // logger.info(msg);
        logger.log(LogUtils.class.getCanonicalName(), Level.WARN, msg, null);
    }

    public static void logError(String msg) {
        // logError(msg, null);
        logger.log(LogUtils.class.getCanonicalName(), Level.ERROR, msg, null);
    }

    public static void logError(String msg, Throwable e) {
    	 logger.log(LogUtils.class.getCanonicalName(), Level.ERROR, msg, e);
    }

    public static void debug(String msg) {
    	logger.debug(msg);
    }
    
    public static void debug (String msg, Throwable t) {
    	logger.debug(msg, t);
    }
    
    public static void info(String msg) {
    	logger.info(msg);
    }
 
    public static void error (String msg) {
    	logger.error(msg);
    }

    public static void error (String msg, Throwable t) {
    	logger.error(msg, t);
    }   
    
    public static boolean isInfoEnabled () {
    	return logger.isInfoEnabled();
    }
    
    public static boolean isDebugEnabled () {
    	return logger.isDebugEnabled();
    }
    
    
}
