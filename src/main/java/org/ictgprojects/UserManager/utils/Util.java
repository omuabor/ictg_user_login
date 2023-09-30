package org.ictgprojects.UserManager.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.ictgprojects.UserManager.exception.CustomException;


import java.security.MessageDigest;
import java.text.SimpleDateFormat;

@UtilityClass
@Slf4j
public class Util {
    public static String createUniqueID()
    {
        String dateTimeString = null;
        java.util.Date requestDateTime = new java.util.Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmSSS");
        dateTimeString = sdf.format(requestDateTime);
        return dateTimeString;
    }

    public String encode(String detail) {
        byte[] bean = null;
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.update(detail.getBytes());
            bean = messageDigest.digest();
        }catch(Exception e){
            log.info("issue with encoding occured: {}", e.getCause().toString());
            throw new CustomException("FAILED", "55", "Operation failed" );
        }
        return stringify(bean);
    }

    public String stringify(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for(final byte b : bytes) {
            sb.append(hex[(b & 0xF0) >> 4]);
            sb.append(hex[b & 0x0F]);
        }
        return sb.toString();
    }

    private static final char[] hex = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };



}
