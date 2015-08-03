package com.appsdaily.payment.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.appsdaily.payment.error.PciPaymentErrorCode;
import com.appsdaily.payment.error.PciPaymentErrorMsg;
import com.onward.scm.exceptions.AppsDailyException;

public class TransactionDbInteraction{

    private final Logger logger;

    public TransactionDbInteraction() {

        logger = Logger.getLogger( this.getClass().getName() );
    }

    
    public boolean updateTransactionLevel( long transaction_id, String onLevel, Connection con ) throws AppsDailyException {
        boolean flag=false;
        try(PreparedStatement psmt=con.prepareStatement( Queries.updateTransactionLevel())){
            int i = 1;
            psmt.setString( i++, onLevel );
            psmt.setLong( i++,transaction_id);
            int j = psmt.executeUpdate();
            if ( j>0 ) {
                flag = true;
            }
        } catch ( SQLException e ) {
            logger.error( "[PciPaymentService Error][TransactionDbInteraction][updateTransactionLevel] " + e.getMessage(), e );
            throw new AppsDailyException( PciPaymentErrorCode.DATABASE_ERROR, PciPaymentErrorMsg.DATABASE_ERROR, e );
        }catch ( Exception e ) {
            logger.error( "[PciPaymentService Error][TransactionDbInteraction][updateTransactionLevel] " + e.getMessage(), e );
            throw new AppsDailyException( PciPaymentErrorCode.SYSTEM_ERROR, PciPaymentErrorMsg.SYSTEM_ERROR, e );
        }
        return flag;
    }

    




}
