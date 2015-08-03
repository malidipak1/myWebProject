/**
 * 
 */
package com.onward.payment.service;


/**
 * @author vinod.talapa
 *
 */
public class CardVault {
	
	/*public String saveThisCard(UserPaymentDetailsBean userPaymentDetailsBean) {
		String token="";
		try(Connection con=DBConnection.getJbossConnection(DBConnection.DB_NAME_PAYMENTS)){
			PCISecurity obj = new PCISecurity(con);
			
			CardDataBean cardDataBean=new CardDataBean();
			CardVaultDBInteraction dbInteration=new CardVaultDBInteraction();
			cardDataBean.setCard_number(obj.encrypt(userPaymentDetailsBean.getCardNumber()));
			Random r=new Random();
			long random_num=r.nextLong();
			token=DateUtils.getCurrentTimeInMilliSeconds()+"-"+Math.abs(random_num)+"-"+userPaymentDetailsBean.getSubId()+"-"+userPaymentDetailsBean.getGuestId()+"-"+userPaymentDetailsBean.getPartner_id()+"-"+userPaymentDetailsBean.getProduct_id();
			cardDataBean.setCard_token(token);
			cardDataBean.setCard_type(userPaymentDetailsBean.getIssuingBank());
			cardDataBean.setExpiry_month(obj.encrypt(userPaymentDetailsBean.getExpiryMonth()));
			cardDataBean.setExpiry_year(obj.encrypt(userPaymentDetailsBean.getExpiryYear()));
			cardDataBean.setSubscriber_id(userPaymentDetailsBean.getSubId());
			cardDataBean.setGuest_id(userPaymentDetailsBean.getGuestId());
			cardDataBean.setPartner_id(userPaymentDetailsBean.getPartner_id());
			cardDataBean.setProduct_id(userPaymentDetailsBean.getProduct_id());
			cardDataBean.setHash_value(CardDataHelper.generateHash(userPaymentDetailsBean.getCardNumber()+"|"+userPaymentDetailsBean.getExpiryMonth()+"|"+userPaymentDetailsBean.getExpiryYear()));
			cardDataBean.setCard_category(userPaymentDetailsBean.getCard_category());
			String cardNum = userPaymentDetailsBean.getCardNumber();
			cardDataBean.setMaskeddigits(cardNum.substring(0,4)+"XXXXXXXX"+cardNum.substring(cardNum.length() - 4, cardNum.length()));
			
			
	        
			if(!dbInteration.card_alreadyExist(cardDataBean.getSubscriber_id(), cardDataBean.getGuest_id(), cardDataBean.getProduct_id(), cardDataBean.getPartner_id(), cardDataBean.getHash_value(), con)){
				dbInteration.insertUserPaymentDetails(cardDataBean, con);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (AppsDailyException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return token;
		
	}
*/
	
}
