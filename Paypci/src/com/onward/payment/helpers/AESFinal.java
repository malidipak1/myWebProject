/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.onward.payment.helpers;
/**
 * 
 * @author 74301
 */
public class AESFinal {

/*public static String encrypt(String text, byte[] iv, byte[] key)
throws Exception{
                        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

                        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
                        IvParameterSpec ivSpec = new IvParameterSpec(iv);

                        cipher.init(Cipher.ENCRYPT_MODE,keySpec,ivSpec);
                        byte [] results =cipher.doFinal(text.getBytes("UTF-8"));
                        BASE64Encoder encoder = new BASE64Encoder();
                        return encoder.encode(results);
        }

        public static String decrypt(String text, byte[] iv, byte[] key)
throws Exception{
                        Cipher cipher =Cipher.getInstance("AES/CBC/PKCS5Padding");

                        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
                        IvParameterSpec ivSpec = new IvParameterSpec(iv);
                    cipher.init(Cipher.DECRYPT_MODE,keySpec,ivSpec);

                        BASE64Decoder decoder = new BASE64Decoder();
                        byte [] results =cipher.doFinal(decoder.decodeBuffer(text));
                        return new String(results,"UTF-8");


        }*/
}
