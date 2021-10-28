package co.mz.policemanager.utils;

import java.security.SecureRandom;
import java.util.Random;

public class GeneratedCarCode {

    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVXWYZabcdefghijklmnopqrstuvxwyz";

    public String codeLength(int length){
        return generatedCode(length);
    }

    protected String generatedCode(int end){
        StringBuilder string  = new StringBuilder();
        Random random = new SecureRandom();
        for (int start = 0; start < end; start++){
            string.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }
        return string.toString();
    }
}
