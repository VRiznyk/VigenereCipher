package com.kage.vigenerecipher;

public interface Encryptor {

    boolean isCorrectKey(String key);

    String encrypt(String key, String text);

    String decrypt(String key, String encryptedText);
}
