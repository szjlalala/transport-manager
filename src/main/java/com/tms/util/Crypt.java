package com.tms.util;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Crypt {
	
    private static String Algorithm = "DES"; // 定义 加密算法,可用 DES,DESede,Blowfish
    //打票数据加密KEY
    public static final String KEY="ylecloud";
    static boolean debug = false;
    // 生成密钥, 注意此步骤时间比较长
    public static byte[] getKey() throws Exception {
        KeyGenerator keygen = KeyGenerator.getInstance(Algorithm);
        SecretKey deskey = keygen.generateKey();
        if (debug)
            System.out.println("生成密钥:" + byte2hex(deskey.getEncoded()));
        return deskey.getEncoded();
    }
    // 加密
    public static String encode(String input, String key){
        return byte2hex(encode(input.getBytes(), key.getBytes()));
    }
    // 加密
    public static byte[] encode(byte[] input, byte[] key){
        SecretKey deskey = new javax.crypto.spec.SecretKeySpec(key, Algorithm);
        if (debug) {
            System.out.println("加密前的二进串:" + byte2hex(input));
            System.out.println("加密前的字符串:" + new String(input));
        }
        Cipher c1=null;
		try {
			c1 = Cipher.getInstance(Algorithm);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
        try {
			c1.init(Cipher.ENCRYPT_MODE, deskey);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
        byte[] cipherByte=null;
		try {
			cipherByte = c1.doFinal(input);
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
        if (debug)
            System.out.println("加密后的二进串:" + byte2hex(cipherByte));
        return cipherByte;
    }
    // 解密
    public static String decode(String input, String key){
        try {
			return new String(decode(hex2byte(input), key.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
        return null;
    }
    // 解密
    public static byte[] decode(byte[] input, byte[] key) throws Exception {
        SecretKey deskey = new javax.crypto.spec.SecretKeySpec(key, Algorithm);
        if (debug)
            System.out.println("解密前的信息:" + byte2hex(input));
        Cipher c1 = Cipher.getInstance(Algorithm);
        c1.init(Cipher.DECRYPT_MODE, deskey);
        byte[] clearByte = c1.doFinal(input);
        if (debug) {
            System.out.println("解密后的二进串:" + byte2hex(clearByte));
            System.out.println("解密后的字符串:" + (new String(clearByte)));
        }
        return clearByte;
    }
    // md5()信息摘要, 不可逆
    public static byte[] md5(byte[] input) throws Exception {
        java.security.MessageDigest alg = java.security.MessageDigest.getInstance("MD5"); // or "SHA-1"
        if (debug) {
            System.out.println("摘要前的二进串:" + byte2hex(input));
            System.out.println("摘要前的字符串:" + new String(input));
        }
        alg.update(input);
        byte[] digest = alg.digest();
        if (debug)
            System.out.println("摘要后的二进串:" + byte2hex(digest));
        return digest;
    }
    // 字节码转换成16进制字符串
    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
            // if (n < b.length - 1)
            // hs = hs + ":";
        }
        // System.out.println("hs="+hs);
        return hs.toUpperCase();
    }
    // 将16进制字符串转换成字节码
    public static byte[] hex2byte(String hex) {
        byte[] bts = new byte[hex.length() / 2];
        for (int i = 0; i < bts.length; i++) {
            bts[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bts;
    }

    public static String SHA1(String decript) {
		try {
			MessageDigest digest = java.security.MessageDigest
					.getInstance("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
    
    public static void main(String[] args) {
		
    	
    	String encode = encode("sss", KEY);
    	System.out.println(encode);
    	String decode = decode(encode, KEY);
    	System.out.println(decode);
    	
    	
	}
}