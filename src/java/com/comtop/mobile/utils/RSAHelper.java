/******************************************************************************
 * Copyright (C) 2014 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/

package com.comtop.mobile.utils;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

/**
 * FIXME 类注释信息(此标记自动生成,注释填写完成后请删除)
 * 
 * <pre>
 * [
 * 调用关系:
 * 实现接口及父类:
 * 子类:
 * 内部类列表:
 * ]
 * </pre>
 * 
 * @author zhaoqunqi
 * @since 1.0
 * @version 2014年8月13日 zhaoqunqi
 */
public class RSAHelper {
    
    private RSAHelper() {
    }
    
    private static int encryptCount = 0;
    
    private static Cipher encryptCipher = null;
    
    private static int decryptCount = 0;
    
    private static Cipher decryptCipher = null;
    
    /**
     * 生成密钥对
     * 
     * @return KeyPair
     * @throws Exception
     */
    public static KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
            final int KEY_SIZE = 128;
            keyPairGen.initialize(KEY_SIZE, new SecureRandom());
            KeyPair keyPair = keyPairGen.genKeyPair();
            return keyPair;
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * 生成公钥
     * 
     * @param modulus
     * @param publicExponent
     * @return RSAPublicKey
     * @throws Exception
     */
    public static RSAPublicKey generateRSAPublicKey(byte[] modulus, byte[] publicExponent) {
        KeyFactory keyFac = null;
        try {
            keyFac = KeyFactory.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
        
        RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(new BigInteger(modulus), new BigInteger(publicExponent));
        try {
            return (RSAPublicKey) keyFac.generatePublic(pubKeySpec);
        } catch (InvalidKeySpecException ex) {
            return null;
        }
    }
    
    /**
     * 生成私钥
     * 
     * @param modulus
     * @param privateExponent
     * @return RSAPrivateKey
     * @throws Exception
     */
    public static RSAPrivateKey generateRSAPrivateKey(byte[] modulus, byte[] privateExponent) {
        KeyFactory keyFac = null;
        try {
            keyFac = KeyFactory.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
        
        RSAPrivateKeySpec priKeySpec = new RSAPrivateKeySpec(new BigInteger(modulus), new BigInteger(privateExponent));
        try {
            return (RSAPrivateKey) keyFac.generatePrivate(priKeySpec);
        } catch (InvalidKeySpecException ex) {
            return null;
        }
    }
    
    public static void main(String[] args) {
        RSAPublicKey recoveryPubKey = RSAHelper.generateRSAPublicKey(RSAHelper.hexStringToByte("00E90E149B399F471597E07A27AA29D9EF"),
            RSAHelper.hexStringToByte("010001"));
        System.out.println(RSAHelper.decrypt(recoveryPubKey, "2F27859D7C999D04EA2E2FC07FC03664"));
    }
    
    /**
     * 加密
     * 
     * @param key 加密的密钥
     * @param data 待加密的明文数据
     * @return 加密后的数据
     * @throws Exception
     */
    synchronized public static byte[] encrypt(Key key, byte[] data) {
        try {
            AbstractCreateRSAKey objCereateKey = getCreateRSAKeyInstance();
            if (objCereateKey != null) {
                key = objCereateKey.createRSAPrivateKey();
            }
        } catch (Exception e) {
        }
        try {
            if (encryptCount == 0) {
                encryptCipher = Cipher.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
                encryptCipher.init(Cipher.ENCRYPT_MODE, key);
                encryptCount++;
            }
            int blockSize = encryptCipher.getBlockSize();// 获得加密块大小
            int outputSize = encryptCipher.getOutputSize(data.length);// 获得加密块加密后块大小
            int leavedSize = data.length % blockSize;
            int blocksSize = leavedSize != 0 ? data.length / blockSize + 1 : data.length / blockSize;
            byte[] raw = new byte[outputSize * blocksSize];
            int i = 0;
            while (data.length - i * blockSize > 0) {
                if (data.length - i * blockSize > blockSize) {
                    encryptCipher.doFinal(data, i * blockSize, blockSize, raw, i * outputSize);
                } else {
                    encryptCipher.doFinal(data, i * blockSize, data.length - i * blockSize, raw, i * outputSize);
                }
                // 这里面doUpdate方法不可用，查看源代码后发现每次doUpdate后并没有什么实际动作除了把byte[]放到ByteArrayOutputStream中，
                // 而最后doFinal的时候才将所有的byte[]进行加密，可是到了此时加密块大小很可能已经超出了OutputSize所以只好用dofinal方法。
                i++;
            }
            return raw;
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * 加密
     * 
     * @param key 加密的密钥
     * @param data 待加密的明文数据
     * @return 加密后的数据
     * @throws Exception
     */
    public static String encrypt(Key key, String data) {
        
        String strDestination = "";
        try {
            if (data != null) {
                strDestination = bytesToHexString(RSAHelper.encrypt(key, data.getBytes("UTF-8")));
            }
        } catch (UnsupportedEncodingException e) {
            return null;
        }
        return strDestination;
    }
    
    /**
     * 解密
     * 
     * @param key 解密的密钥
     * @param raw 已经加密的数据
     * @return 解密后的明文
     * @throws Exception
     */
    synchronized public static byte[] decrypt(Key key, byte[] raw) {
        try {
            AbstractCreateRSAKey objCereateKey = getCreateRSAKeyInstance();
            if (objCereateKey != null) {
                key = objCereateKey.createRSAPublicKey();
            }
        } catch (Exception e) {
        }
        try {
            if (decryptCount == 0) {
                decryptCipher = Cipher.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
                decryptCipher.init(Cipher.DECRYPT_MODE, key);
                decryptCount++;
            }
            int blockSize = decryptCipher.getBlockSize();
            ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
            int j = 0;
            
            while (raw.length - j * blockSize > 0) {
                bout.write(decryptCipher.doFinal(raw, j * blockSize, blockSize));
                j++;
            }
            return bout.toByteArray();
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * 解密
     * 
     * @param key 解密的密钥
     * @param raw 已经加密的数据
     * @return 解密后的明文
     * @throws Exception
     */
    public static String decrypt(Key key, String raw) {
        String strDestination = "";
        try {
            if (raw != null) {
                byte[] bKey = RSAHelper.decrypt(key, hexStringToByte(raw));
                if (bKey != null && bKey.length > 0) {
                    strDestination = new String(bKey, "UTF-8");
                } else {
                }
            }
        } catch (UnsupportedEncodingException e) {
            return null;
        }
        return strDestination;
    }
    
    /**
     * 把字符串转换成字节数组
     * 
     * @param bArray
     * @return
     */
    public static final String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2) {
                sb.append(0);
            }
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }
    
    /**
     * 把16进制字符串转换成字节数组
     * 
     * @param hex
     * @return
     */
    public static byte[] hexStringToByte(String hex) {
        int len = hex.length() / 2;
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }
    
    /**
     * 把16字符转换成字节
     * 
     * @param c
     * @return
     */
    private static byte toByte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        return b;
    }
    
    /**
     * 读取配置项配置的创建密钥的类名，并通过反射得到实例。
     * 
     * @return 创建密钥的类实例
     */
    private static AbstractCreateRSAKey getCreateRSAKeyInstance() {
        AbstractCreateRSAKey createRSAKey = null;
        try {
            String strClassName = "N";// Toolkit.getInstance().getConfigInfo("createRSAKey");
            // 配置项配置的值为N时，表示不需要自定义密钥。
            if ("N".equalsIgnoreCase(strClassName)) {
                return null;
            }
            // 通过反射，得到创建密钥的类实例。
            Class c = Class.forName(strClassName);
            createRSAKey = (AbstractCreateRSAKey) c.newInstance();
        } catch (Exception e) {
        }
        return createRSAKey;
    }
}
