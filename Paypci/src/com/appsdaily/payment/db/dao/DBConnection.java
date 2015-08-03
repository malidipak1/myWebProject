package com.appsdaily.payment.db;

import java.sql.Connection;

import org.apache.log4j.Logger;

import com.appsdaily.payment.error.PciPaymentErrorCode;
import com.appsdaily.payment.error.PciPaymentErrorMsg;
import com.onward.scm.constants.JbossConstants;
import com.onward.scm.db.connection.SCMDBConnection;
import com.onward.scm.exceptions.AppsDailyException;

public class DBConnection {

    private static Logger logger;

    private DBConnection() {
	logger = Logger.getLogger(this.getClass().getName());
    }

    public static final String DB_NAME_SCMLIVE = JbossConstants.DATASOURCE_SCM_LIVE;
    public static final String DB_NAME_APPSPORTAL = JbossConstants.DATASOURCE_APPS_STORE;

    // public static final String DB_NAME_PAYMENTS=JbossConstants.DATASOURCE_PAYMENTS;

    public static Connection getConnection() throws AppsDailyException {
	try {
	    return getJbossConnection(DB_NAME_SCMLIVE);
	} catch (AppsDailyException e) {
	    throw new AppsDailyException(PciPaymentErrorCode.DATABASE_ERROR, PciPaymentErrorMsg.DATABASE_ERROR, e);
	}
    }

    public static final Connection getJbossConnection(String dbName) throws AppsDailyException {
	if (DB_NAME_SCMLIVE.equals(dbName)) {
	    return SCMDBConnection.getSCMConnection();
	} else if (DB_NAME_APPSPORTAL.equals(dbName)) {
	    return SCMDBConnection.getAppsStoreConnection();
	}/*
	  * else if (DB_NAME_PAYMENTS.equals(dbName)) { return PaymentDBConnnection.getPaymentConnection(); }
	  */
	logger.error("Database name in invalid : " + dbName);
	throw new AppsDailyException(PciPaymentErrorCode.DATABASE_ERROR, PciPaymentErrorMsg.DATABASE_ERROR);
    }
}
