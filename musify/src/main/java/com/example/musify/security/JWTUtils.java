package com.example.musify.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.musify.MusifyApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class JWTUtils {
    private static final String issuer = "musify";

    private Logger log= LoggerFactory.getLogger(JWTUtils.class);

    @Value("${jwt.secret:secret}")
    private String signatureSecret;

    @PostConstruct
    public void init(){
        log.info("SECRET: {}",signatureSecret);
    }


    public String generateToken(Integer userId,Integer role,String email){
        Algorithm algorithm = Algorithm.HMAC256(signatureSecret);

        Calendar c = Calendar.getInstance();
        Date currentDate = c.getTime();

        c.add(Calendar.MINUTE, 1);
        Date expireDate = c.getTime();

        return JWT.create()
                .withIssuer(issuer)
                .withSubject(issuer)
                .withIssuedAt(currentDate)
                .withExpiresAt(expireDate)
                .withClaim("userId", userId)
                .withClaim("email",email)
                .withClaim("role", role)
                .sign(algorithm);
    }

    public List<Object> validateToken(String jwtToken) {
        Algorithm algorithm = Algorithm.HMAC256(signatureSecret);

        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(issuer)
                .withSubject(issuer)
                .build();

        DecodedJWT decodedJWT = verifier.verify(jwtToken);
        List<Object> userInfo = new ArrayList<>();
        userInfo.add(decodedJWT.getClaim("userId").asInt());
        userInfo.add(decodedJWT.getClaim("role").asString() );
        userInfo.add(decodedJWT.getClaim("email").asString());
       // Integer userId = decodedJWT.getClaim("userId").asInt()
        //String role = decodedJWT.getClaim("role").asString();

        return userInfo;
    }
}
