package com.appsdaily.payment.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.onward.payment.beans.CardDataBean;
import com.onward.payment.beans.MaskedCardBean;
import com.onward.scm.utility.DateUtils;

public class CardVaultDBInteraction {
    
    Logger log;
    
    public CardVaultDBInteraction() {
        log = Logger.getLogger( this.getClass().getName() );
    }
    
    
    
    public boolean insertUserPaymentDetails(CardDataBean cBean, Connection conn ) {
        String query =
                "INSERT INTO user_payment_details (card_token,subscriber_id,guest_id,product_id,partner_id,card_type,card_number,expiry_month,expiry_year,hash_value,creation_date,maskedcard,card_category) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try(PreparedStatement pstmt = conn.prepareStatement(query)) {
            int i=1;
            pstmt.setString(i++,cBean.getCard_token());
            pstmt.setLong(i++,cBean.getSubscriber_id());
            pstmt.setLong(i++,cBean.getGuest_id());
            pstmt.setLong(i++,cBean.getProduct_id());
            pstmt.setLong(i++,cBean.getPartner_id());
            pstmt.setString(i++,cBean.getCard_type());
            pstmt.setString(i++,cBean.getCard_number());
            pstmt.setString(i++,cBean.getExpiry_month());
            pstmt.setString(i++,cBean.getExpiry_year());
            pstmt.setString(i++, cBean.getHash_value());
            pstmt.setLong(i++, DateUtils.getCurrentTimeInMilliSeconds());
            pstmt.setString(i++,cBean.getMaskeddigits());
            pstmt.setString(i++, cBean.getCard_category());
            int r=pstmt.executeUpdate();
            if(r>=1){
            	return true;
            }
        } catch ( Exception e ) {
            log.error( "[PciPaymentService Error]Error-insertUserPaymentDetails :" + e );
        }
        return false;
    }    
    
    public boolean updateUserPaymentDetails(CardDataBean cBean, Connection conn ) {
        String query =
                "UPDATE user_payment_details set card_category=?,card_type=?,card_number=?,expiry_month=?,expiry_year=?,hash_value=?,modification_date=?,maskedcard=? where card_token=? and (subscriber_id=? or (guest_id=? and partner_id=? and product_id=?));";
        try(PreparedStatement pstmt = conn.prepareStatement(query)) {
            int i=1;
            pstmt.setString(i++, cBean.getCard_category());
            pstmt.setString(i++,cBean.getCard_type());
            pstmt.setString(i++,cBean.getCard_number());
            pstmt.setString(i++,cBean.getExpiry_month());
            pstmt.setString(i++,cBean.getExpiry_year());
            pstmt.setString(i++, cBean.getHash_value());
            pstmt.setLong(i++, DateUtils.getCurrentTimeInMilliSeconds());
            pstmt.setString(i++,cBean.getMaskeddigits());
            int r=pstmt.executeUpdate();
            if(r>=1){
            	return true;
            }
        } catch ( Exception e ) {
            log.error( "[PciPaymentService Error]Error-updateUserPaymentDetails :" + e );
        }
        return false;
    }
    
    public List<MaskedCardBean> getMaskedCard(long subid,long guest_id,long product_id,long partner_id, Connection conn){
    	List<MaskedCardBean> listOfCards=new ArrayList<MaskedCardBean>();
    	String query =
                "SELECT maskedcard,card_type,card_token,card_category from user_payment_details where (subscriber_id=? or (guest_id=? and partner_id=? and product_id=?));";
        try(PreparedStatement pstmt = conn.prepareStatement(query)) {
            int i=1;
            pstmt.setLong(i++,subid);
            pstmt.setLong(i++,guest_id);
            pstmt.setLong(i++,partner_id);
            pstmt.setLong(i++,product_id);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
            	MaskedCardBean bean=new MaskedCardBean();
            	bean.setMaskedcard(rs.getString(1));
            	bean.setCard_type(rs.getString(2));
            	bean.setCard_token(rs.getString(3));
            	bean.setCard_category(rs.getString(4));
            	listOfCards.add(bean);
            	
            }
        } catch ( Exception e ) {
            log.error( "[PciPaymentService Error]Error-getMaskedCard :" + e );
        }
        return listOfCards;
    }
    
    public boolean card_alreadyExist(long subid,long guest_id,long product_id,long partner_id,String hashvalue, Connection conn){
    	String query =
                "SELECT * from user_payment_details where ((subscriber_id=? or (guest_id=? and partner_id=? and product_id=?)) and hash_value=?);";
        try(PreparedStatement pstmt = conn.prepareStatement(query)) {
            int i=1;
            pstmt.setLong(i++,subid);
            pstmt.setLong(i++,guest_id);
            pstmt.setLong(i++,partner_id);
            pstmt.setLong(i++,product_id);
            pstmt.setString(i++,hashvalue);
            ResultSet rs=pstmt.executeQuery();
            if(rs.next()){
            	return true;
            }
        } catch ( Exception e ) {
            log.error( "[PciPaymentService Error]Error-card_alreadyExist :" + e );
        }
        return false;
    }
    
    
}
