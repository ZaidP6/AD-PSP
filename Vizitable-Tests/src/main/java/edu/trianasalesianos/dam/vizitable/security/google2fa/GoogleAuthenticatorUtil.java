package edu.trianasalesianos.dam.vizitable.security.google2fa;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;

public class GoogleAuthenticatorUtil {

    private static final GoogleAuthenticator gAuth = new GoogleAuthenticator();

    public static String generateSecretKey() {
        GoogleAuthenticatorKey key = gAuth.createCredentials();
        return key.getKey();
    }

    public static boolean validateCode(String secretKey, int code) {
        return gAuth.authorize(secretKey, code);
    }
}
