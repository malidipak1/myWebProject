package com.appsdaily.payment.analytics;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import com.onward.scm.utility.DateUtils;

public class AnalyticsDBInteraction {
Logger log;
    
    public AnalyticsDBInteraction() {
        log = Logger.getLogger( this.getClass().getName() );
    }
    
    
    
    public boolean insertIntoAnalyticsLogs(AnalyticsActionLogDOBean aBean, Connection conn ) {
        String query =
                "INSERT INTO ana_action_log (action_id,subscriber_id,guest_id,product_id,partner_id,time_stamp,ap1_value,ap2_value,ap3_value,ap4_value,ap5_value,creation_date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try(PreparedStatement pstmt = conn.prepareStatement(query)) {
            int i=1;
            pstmt.setLong(i++, aBean.getActionId());
            pstmt.setLong(i++,aBean.getSubscriberId());
            pstmt.setLong(i++,aBean.getGuest_id());
            pstmt.setLong(i++,aBean.getProductId());
            pstmt.setLong(i++,aBean.getPartnerId());
            pstmt.setLong(i++,aBean.getTimeStamp());
            pstmt.setString(i++,aBean.getAp1Value());
            pstmt.setString(i++,aBean.getAp2Value());
            pstmt.setString(i++,aBean.getAp3Value());
            pstmt.setString(i++,aBean.getAp4Value());
            pstmt.setString(i++,aBean.getAp5Value());
            pstmt.setLong(i++, DateUtils.getCurrentTimeInMilliSeconds());
            int r=pstmt.executeUpdate();
            if(r>=1){
            	return true;
            }
        } catch ( Exception e ) {
            log.error( "[PciPaymentService Error]Error-insertIntoAnalyticsLogs :" + e );
        }
        return false;
    }    
}
