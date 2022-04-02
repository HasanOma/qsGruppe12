package com.example.qsgruppe12.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * TODO javadoc
 */
@Component
public class JWTUtil implements Serializable {

    private String secret = "verysecretive";

    public static final long TOKEN_VALIDITY = 10 * 60 * 60;

    public String getEmailFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Finds experation date of the token
     * @param token JWT token
     * @return Date it is expiring
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    /**
     * Method to see if the token of the user is expired.
     * @param token JWT token
     * @return returns true if the token has time left
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token for user

    /**
     * Method
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return generateToken(claims, userDetails.getUsername());
    }

    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.

    /**
     * Helper method to Generate the JWT token and define the claims ass well
     * as signing it with our secret key and HS512 algorithm
     * @param claims defining claims of the token hashmap
     * @param subject email of the user.
     * @return returns the JWT token
     */
    private String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 10000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    /**
     * Method to validate the token sent in requests
     * @param token JWT token
     * @param userDetails userdetails of the
     * @return returns true if token email and user email are the same
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String email = getEmailFromToken(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
