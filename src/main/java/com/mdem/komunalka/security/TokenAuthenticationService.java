package com.mdem.komunalka.security;

import com.mdem.komunalka.model.Role;
import com.mdem.komunalka.model.User;
import com.mdem.komunalka.service.impl.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
public class TokenAuthenticationService {

    private static final long EXPIRATION_TIME = 600000; // 10 minutes
    private static final String SECRET = "Komunalka";
    private static final String TOKEN_PREFIX = "Bearer";

    public static String createTokenAuthentication(User user) {
        String token = Jwts.builder()
                .setSubject(user.getLogin())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .claim("roles", user.getRoles())
                .claim("id", user.getId())
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        return TOKEN_PREFIX + " " + token;
    }

    public static long getUserIdFromToken(String token) {
        long userId = (long) Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody().get("id");

        return userId;
    }

    public static String getUsernameFromToken(String token) {
        String username = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody().getSubject();

        return username;
    }

    public static List<Role> getRolesFromToken(String token) {
        List roles = (List) Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody().get("roles");

        return roles;
    }
}