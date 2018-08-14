package com.jdp.hls.util;

/**
 * Description:TODO
 * Create Time:2018/8/14 0014 上午 8:56
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AesUtil {

    public static final String keyStr = "aabbccddeeffgghh";


    public static String encrypt(String source) {
        String encrypt = null;
        try {
            encrypt = CXAESUtil.encrypt(keyStr, source);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encrypt;
    }

    public static String decode(String encryptContent) {
        String decrypt = null;
        try {
            decrypt = CXAESUtil.decrypt(keyStr, encryptContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decrypt;
    }

    public static void main(String[] args) {
        String encrypt = encrypt("123456");
        System.out.println("加密后：" + encrypt);
        System.out.println("解密后：" + decode(encrypt));
    }
}
