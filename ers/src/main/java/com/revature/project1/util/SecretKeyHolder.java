package com.revature.project1.util;

import javax.crypto.SecretKey;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class SecretKeyHolder {
    public static SecretKey key = (SecretKey) Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public SecretKeyHolder() {
    }
}
