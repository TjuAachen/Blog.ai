package com.mszlu.blog.utils;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.jasypt.util.text.BasicTextEncryptor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {

    private static final String jwtToken = "123456Mszlu!@#$$";

    public static String createToken(Long userId){
        Map<String,Object> claims = new HashMap<>();
        claims.put("userId",userId);
        JwtBuilder jwtBuilder = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, jwtToken) // Issuing algorithm, the key is jwt Token
                .setClaims(claims) // The body data must be unique, set it yourself
                .setIssuedAt(new Date()) // Set issue time
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 60 * 1000));// valid time of day
        String token = jwtBuilder.compact();
        return token;
    }

    public static Map<String, Object> checkToken(String token){
        try {
            Jwt parse = Jwts.parser().setSigningKey(jwtToken).parse(token);
            return (Map<String, Object>) parse.getBody();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    public static void main(String[] args) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        //The salt required for encryption
        textEncryptor.setPassword("mszlu_blog_$#@wzb_&^%$#");
        //The data to encrypt (username or password for the database)
        String username = textEncryptor.encrypt("root");
        String password = textEncryptor.encrypt("root");
        System.out.println("username:"+username);
        System.out.println("password:"+password);
        System.out.println(textEncryptor.decrypt("29cZ+X9cNmECjbLXT2P/BBZWReVl30NS"));
    }

}
