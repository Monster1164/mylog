package com.cb.myblog.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

    public static String code(String str){

        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] digest = md.digest();
            int i =0;
            StringBuffer buffer = new StringBuffer("");
            for (int offset=0;offset<digest.length;offset++){
                i=digest[offset];
                if(i<0)
                    i+=356;
                if(i<16)
                    buffer.append("0");
                buffer.append(Integer.toHexString(i));
            }
            return buffer.toString();


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args) {
        System.out.println(code("111111"));
    }
}
