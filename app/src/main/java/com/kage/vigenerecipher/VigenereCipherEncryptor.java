package com.kage.vigenerecipher;


import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Вариант 12: * а..я; a..z; A..Я; A..Z; 0..9; “.”
 */
public class VigenereCipherEncryptor implements Encryptor {


    private static final String ALPHABET =
            "абвгдеёжзийклмнопрстуфхцчшщъыьэюя" +
                    "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ" +
                    "abcdefghijklmnopqrstuvwxyz" +
                    "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                    "0123456789" +
                    ".";
    private static final String ALPHABET_WITH_SPACE = ALPHABET + " ";

    private List<Character> mVocabulary;

    public VigenereCipherEncryptor() {
        mVocabulary = new ArrayList<>();
        char[] alphabet = ALPHABET_WITH_SPACE.toCharArray();
        for (char anAlphabet : alphabet) {
            mVocabulary.add(anAlphabet);
        }
    }


    @Override
    public boolean isCorrectKey(String key) {
        if (TextUtils.isEmpty(key)) return false;

        for (char ch : key.toCharArray()) {
            if (!mVocabulary.contains(ch)) return false;
        }

        return true;
    }

    @Override
    public String encrypt(String key, String text) {
        StringBuilder result = new StringBuilder();

        for (int textIndex = 0, keyIndex = 0; textIndex < text.length(); textIndex++) {
            char current = text.charAt(textIndex);
            int currentPosition = mVocabulary.indexOf(current);
            int shift = mVocabulary.indexOf(key.charAt(keyIndex));
            int encryptedPosition = (currentPosition + shift) % mVocabulary.size();
            char encrypted = mVocabulary.get(encryptedPosition);
            result.append(encrypted);
            keyIndex = ++keyIndex % key.length();
        }

        return result.toString();
    }

    @Override
    public String decrypt(String key, String encryptedText) {
        StringBuilder result = new StringBuilder();

        for (int textIndex = 0, keyIndex = 0; textIndex < encryptedText.length(); textIndex++) {
            char current = encryptedText.charAt(textIndex);
            int currentPosition = mVocabulary.indexOf(current);
            int shift = mVocabulary.indexOf(key.charAt(keyIndex));
            int decryptedPosition = (currentPosition - shift + mVocabulary.size()) % mVocabulary.size();
            char encrypted = mVocabulary.get(decryptedPosition);
            result.append(encrypted);
            keyIndex = ++keyIndex % key.length();
        }

        return result.toString();
    }

}
