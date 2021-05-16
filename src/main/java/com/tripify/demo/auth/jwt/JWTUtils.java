package com.tripify.demo.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tripify.demo.exceptions.ExpiredJwtToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtils {

    private static final long serialVersionUID = -2550185165626007488L;
    private static final long hours = 5;
    public static final long JWT_TOKEN_VALIDITY = hours * 60 * 60;

    @Value("${jwt.secret}")
    private String secret;

    //retrieve username from jwt token
    public String getPhoneFromToken(String token)  throws ExpiredJwtToken {
        if(isTokenExpired(token))
            throw new ExpiredJwtToken();
        return JWT.decode(token).getSubject();
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return JWT.decode(token).getExpiresAt();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token for user
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return JWT.create().withClaim("","").withSubject(subject).withExpiresAt(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY * 1000)).sign(Algorithm.HMAC256(secret));
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username;
        try {
            username = getPhoneFromToken(token);
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        } catch (ExpiredJwtToken expiredJwtToken) {
            expiredJwtToken.printStackTrace();
        }
        return false;
    }
}
