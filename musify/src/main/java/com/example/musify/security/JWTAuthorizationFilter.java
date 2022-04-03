package com.example.musify.security;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.musify.exceptions.InvalidTokenException;
import com.example.musify.repo.UserRepo;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";


    private final Logger log = LoggerFactory.getLogger(JWTAuthorizationFilter.class);
    @Autowired
    JWTUtils jwtUtils;

    @Autowired
    UserRepo userRepo;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {
        String header = req.getHeader(HEADER_STRING);

        String token;
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        } else {
            token = header.replaceAll(TOKEN_PREFIX, "").trim();
        }
        if (!userRepo.checkToken(token)) {
            chain.doFilter(req, res);
            try {
                throw new InvalidTokenException("Token is not valid");
            } catch (InvalidTokenException e) {
                e.printStackTrace();
            }
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    // Reads the JWT from the Authorization header, and then uses JWT to validate the token
    private UsernamePasswordAuthenticationToken getAuthentication(String token) {


        List<Object> userInfo = jwtUtils.validateToken(token);
        Integer userId = (Integer) userInfo.get(0);
        Integer role = (Integer) userInfo.get(1);
        String email = (String) userInfo.get(2);

        if (userId != null && role != null && email != null) {
            // new arraylist means authorities
            return new UsernamePasswordAuthenticationToken(userInfo, null, new ArrayList<>());
        }

        return null;
    }

}
