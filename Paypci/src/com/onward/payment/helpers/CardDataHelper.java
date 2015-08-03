package com.onward.payment.helpers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CardDataHelper {
	public static String generateHash(String str){
		try {
			return makeSHA1Hash(str);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public static String makeSHA1Hash(String input)
            throws NoSuchAlgorithmException
        {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.reset();
            byte[] buffer = input.getBytes();
            md.update(buffer);
            byte[] digest = md.digest();

            String hexStr = "";
            for (int i = 0; i < digest.length; i++) {
                hexStr +=  Integer.toString( ( digest[i] & 0xff ) + 0x100, 16).substring( 1 );
            }
            return hexStr;
        }
}
