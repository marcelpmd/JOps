package com.jops.service;

/**
 * Created by MKowynia on 9/29/15.
 */
public interface IEncryptionService {
    String encrypt(String s);

    String decrypt(String s);
}
