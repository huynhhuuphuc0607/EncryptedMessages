package edu.orangecoastcollege.cs273.encryptedmessages;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * Created by HuynhHuu on 13-Dec-17.
 */

public class AESEncription {
    public static String ALGORITHM = "AES";
    private SecretKey secretKey;
    private Cipher cipher;
    private SecureRandom rand;

    public AESEncription()
    {
        try {
            cipher = Cipher.getInstance(ALGORITHM);
            rand = new SecureRandom();
            KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
            keyGen.init(256);
            secretKey = keyGen.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    public String crypt(int opMode, String message, SecretKey key)
    {
        try {
            cipher.init(opMode,key,rand);
            byte[] messageBytes = message.getBytes("ISO-8859-1");
            byte[] encodedBytes = cipher.doFinal(messageBytes);
            String encoded = new String(encodedBytes, "ISO-8859-1");
            return encoded;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }

    public byte[] getkeyBytes() {
        return secretKey.getEncoded();
    }
}
