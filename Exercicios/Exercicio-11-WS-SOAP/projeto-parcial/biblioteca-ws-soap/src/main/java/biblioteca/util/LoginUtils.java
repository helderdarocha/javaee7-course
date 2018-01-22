/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author helderdarocha
 */
public class LoginUtils {
    
    public static String getPasswordHash(String password) {
        return convertToHex(makePasswordHash(password));
    }

    public static byte[] makePasswordHash(String password) {
        byte[] hash = null;
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            sha.update(password.getBytes(StandardCharsets.UTF_8));
            hash = sha.digest();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginUtils.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Configuration error: missing algorithm. Unable to setup realm.");
        }
        return hash;
    }

    public static String convertToHex(byte[] hash) {
        StringBuilder buffer = new StringBuilder();
        for (byte b : hash) {
            int number = 255 & b;
            String hexDigit = Integer.toHexString(number);
            if (hexDigit.length() == 1) {
                buffer.append('0');
            }
            buffer.append(hexDigit);
        }
        return buffer.toString();
    }
}
