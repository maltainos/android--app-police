package co.mz.policemanager.utils;

import java.security.SecureRandom;
import java.util.Random;

public class GeneratedPoliceCode{

    private static final String DIGITS = "0123456789";

    public String codeLength(int length){
       return generatedCode(length);
    }

    protected String generatedCode(int end){
        StringBuilder string  = new StringBuilder();
        Random random = new SecureRandom();
        for (int start = 0; start < end; start++){
            string.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        }
        return string.toString();
    }
}
