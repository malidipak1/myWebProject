package com.appsdaily.payment.db;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.appsdaily.payment.error.PciPaymentErrorCode;
import com.appsdaily.payment.error.PciPaymentErrorMsg;
import com.onward.scm.exceptions.AppsDailyException;

public class DAOServiceHelper{
    static Logger logger;

    public DAOServiceHelper() {
    	logger=Logger.getLogger(this.getClass().getName());
	}
    public static void startTransaction(Connection con) throws AppsDailyException{
	if(con != null){
	    try {
		con.setAutoCommit(false);
	    } catch (SQLException e) {
    	logger.error("[PciPaymentService Error][DAOServiceHelper][startTransaction] Transaction couldn't get intialized");
		throw new AppsDailyException(PciPaymentErrorCode.DATABASE_ERROR, PciPaymentErrorMsg.DATABASE_ERROR, e);
	    }
	}
	else {
	    throw new AppsDailyException(PciPaymentErrorCode.DATABASE_ERROR_NULL_CONNECTION, PciPaymentErrorMsg.DATABASE_ERROR_NULL_CONNECTION);
	}
    }

    public static void endTransaction(boolean commit, Connection con) throws AppsDailyException{
	if(commit) {
	    commitTransaction(con);
	} else {
	    rollbackTransaction(con);
	}
    }

    protected static void commitTransaction(Connection con) throws AppsDailyException {
	if(con != null){
	    try {
		con.commit();
		con.setAutoCommit(true);
	    } catch (SQLException e) {
		logger.error("[PciPaymentService Error][DAOServiceHelper][commitTransaction] Transaction couldn't not Commeted");
		throw new AppsDailyException(PciPaymentErrorCode.DATABASE_ERROR, PciPaymentErrorMsg.DATABASE_ERROR, e);
	    }
	}
	else {
	    logger.error("[PciPaymentService Error][DAOServiceHelper][commitTransaction] Transation Was not commited as Connection was null");
	    throw new AppsDailyException(PciPaymentErrorCode.DATABASE_ERROR_NULL_CONNECTION, PciPaymentErrorMsg.DATABASE_ERROR_NULL_CONNECTION);
	}
    }

    protected static void rollbackTransaction(Connection con) throws AppsDailyException {
	if(con != null){
	    try {
		con.rollback();
		con.setAutoCommit(true);
	    } catch (SQLException e) {
		logger.error("[PciPaymentService Error][DAOServiceHelper][rollbackTransaction] Transation Was not rolledBack,");
		throw new AppsDailyException(PciPaymentErrorCode.DATABASE_ERROR, PciPaymentErrorMsg.DATABASE_ERROR, e);
	    }
	}
	else {
	    logger.error("[PciPaymentService Error][DAOServiceHelper][rollbackTransaction] Transation Was not rolledBack as Connection was null");
	    throw new AppsDailyException(PciPaymentErrorCode.DATABASE_ERROR_NULL_CONNECTION, PciPaymentErrorMsg.DATABASE_ERROR_NULL_CONNECTION);
	}
    }

    public static void  closeConnection(Connection con) {
	//		DBConnection.releaseResource(con);
	try{
	    if(con != null) {
	    	con.setAutoCommit(true);
	    	con.close();
	    }
	}catch (SQLException e){
	}
    }

}
