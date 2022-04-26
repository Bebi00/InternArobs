package com.example.musify.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.musify.exceptions.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class JWTUtils {

    private final Logger log = LoggerFactory.getLogger(JWTUtils.class);

    @Value("${jwt.secret:secret}")
    private String signatureSecret;

    @Value("${jwt.issuer:musify}")
    private String issuer;

    @PostConstruct
    public void init() {
        log.info("SECRET: {}", signatureSecret);
        log.info("Issuer: {}", issuer);
    }


    public Object[] generateToken(Long userId, Integer role, String email) {
        Algorithm algorithm = Algorithm.HMAC256(signatureSecret);

        Calendar c = Calendar.getInstance();
        Date currentDate = c.getTime();

        c.add(Calendar.HOUR, 1);
        Date expireDate = c.getTime();

        String token = JWT.create()
                .withIssuer(issuer)
                .withSubject(issuer)
                .withIssuedAt(currentDate)
                .withExpiresAt(expireDate)
                .withClaim("userId", userId)
                .withClaim("email", email)
                .withClaim("role", role)
                .sign(algorithm);
        return new Object[]{token,expireDate};
    }

    public List<Object> validateToken(String jwtToken) {
        Algorithm algorithm = Algorithm.HMAC256(signatureSecret);

        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(issuer)
                .withSubject(issuer)
                .build();

        DecodedJWT decodedJWT = verifier.verify(jwtToken);
        List<Object> userInfo = new ArrayList<>();
        userInfo.add(decodedJWT.getClaim("userId").asLong());
        userInfo.add(decodedJWT.getClaim("role").asInt());
        userInfo.add(decodedJWT.getClaim("email").asString());
        userInfo.add(decodedJWT.getExpiresAt());
        // Integer userId = decodedJWT.getClaim("userId").asInt()
        //String role = decodedJWT.getClaim("role").asString();
        return userInfo;
    }

    public String getToken(String header) {
        String token = header.replaceAll("Bearer ", "").trim();
        return token;
    }

    public Long getUserId(){
        List<?> userInfo = (List<?>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (Long) userInfo.get(0);
    }

    public static void checkUserRoleAdmin(){
        List<?> userInfo = (List<?>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if((int)userInfo.get(1) != 1){
            throw new UnauthorizedException("Only Admin Users can perform this action");
        }
    }


}
