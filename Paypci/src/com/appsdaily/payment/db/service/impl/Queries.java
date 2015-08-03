/**
 *
 */
package com.appsdaily.payment.db.dao;

/**
 * @author vinod.talapa
 *
 */
public class Queries {


    public static String updateTransactionLevel() {
        String query="update transactions set level=? where transaction_id=?";
        return query;
    }



}
