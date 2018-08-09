package com.jdp.hls.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Description:TODO
 * Create Time:2018/7/6 15:18
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class EncryptUtil {

    public static String getDoubleMd5(String text) {
        return getMd5(getMd5(text));
    }

    public static String getMd5(String text) {
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(text.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            re_md5 = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return re_md5;
    }

    public static String GetSignature(long timeStamp, String token) {
        String signature = timeStamp + token;
        char[] chars = signature.toCharArray();
        Arrays.sort(chars);
        signature = getMd5(String.valueOf(chars)).toUpperCase();
        return signature;
    }

    public static void main(String[] args) {
        System.out.println("排序后:" + GetSignature(1533692048653L, "98f8a2d67d5e454f85c2469696fa66ee"));
    }
}
