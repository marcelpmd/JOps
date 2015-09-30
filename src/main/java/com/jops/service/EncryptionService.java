package com.jops.service;

import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by MKowynia on 9/29/15.
 */
public class EncryptionService implements IEncryptionService {

    private String secret;

    public EncryptionService(String secret){
        this.secret = secret;
    }

    public String encrypt(String s){
        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword(secret);
        return basicTextEncryptor.encrypt(s);
    }

    public String decrypt(String s){
        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword(secret);
        return basicTextEncryptor.decrypt(s);
    }
}
