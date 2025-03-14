package org.dogra.stockflow.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.dogra.stockflow.config.User.CatUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.security.auth.kerberos.EncryptionKey;
import java.util.Base64;
import java.util.Date;


@Component
public class JwtUtil {

    final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
    private String secretKey = "secretKey";

    public JwtUtil() {
        try {
            KeyGenerator keygen = KeyGenerator.getInstance("HmacSHA256");
            secretKey = Base64.getEncoder().encodeToString(keygen.generateKey().getEncoded());

        } catch (Exception e) {
            logger.warn("Auto secretkey generation failed");
            secretKey = "jajfasjiwesamjsd23rjwu9sjidfaskf9w8ruw9849sdkfsdfejrh435u89qwpqjnvac";
        }
    }


    public String genTokens(CatUserDetails user) {

        String username = user.getUsername();
        String roles = user.getAuthorities().toString();

        try {

            return Jwts.builder()
                    .setSubject(username)
                    .claim("roles", roles)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 + 1000))
                    .signWith(SignatureAlgorithm.HS256, getSecretKey().getBytes())
                    .compact();
        } catch (Exception e) {
            logger.error("jwt token generation failed -- " + e.getMessage());
        }


        return "";
    }

    private String getSecretKey() {
        return secretKey;
    }


    public boolean validateTokens(String tokens, CatUserDetails userDetails){

        Claims claims = extractClaims(tokens);

        if(claims == null) {
            return false;
        }else if(!claims.getSubject().equals(userDetails.getUsername())){
            return false;
        } else if (claims.getExpiration().after(new Date(System.currentTimeMillis()))) {
            return false;
        }

        return true;
    }


    public Claims extractClaims(String tokens){

        try{
            return Jwts.parserBuilder()
                    .setSigningKey(getSecretKey().getBytes())
                    .build().parseClaimsJws(tokens)
                    .getBody();
        } catch (Exception e) {
            logger.error("Failed to Extract Claims from Tokens --{}", e.getMessage());
        }

        return null;
    }


}
