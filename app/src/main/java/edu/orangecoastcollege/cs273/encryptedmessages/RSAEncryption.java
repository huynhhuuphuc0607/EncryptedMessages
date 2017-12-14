package edu.orangecoastcollege.cs273.encryptedmessages;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

/**
 * Created by HuynhHuu on 13-Dec-17.
 */

public class RSAEncryption {
    public static String ALGORITHM = "RSA";
    private Cipher cipher;
    private Key privateKey;
    private Key publicKey;

    public RSAEncryption()
    {
        try {
            cipher = Cipher.getInstance(ALGORITHM);
            KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITHM);
            generator.initialize(1024);
            KeyPair keyPair = generator.generateKeyPair();
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    public Key getPrivateKey() {
        return privateKey;
    }

    public Key getPublicKey() {
        return publicKey;
    }

    public byte[] getPrivateKeyBytes() {
        return privateKey.getEncoded();
    }

    public byte[] getPublicKeyBytes() {
        return publicKey.getEncoded();
    }

    public String crypt(int opMode, String message, Key key)
    {
        try {
            cipher.init(opMode,key);
            byte[] messageBytes = message.getBytes("ISO-8859-1");
            byte[] encryptedBytes = cipher.doFinal(messageBytes);
            String encrypted = new String(encryptedBytes, "ISO-8859-1");
            return encrypted;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
