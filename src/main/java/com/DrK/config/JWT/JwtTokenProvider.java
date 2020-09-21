package com.DrK.config.JWT;

import java.util.Base64;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import com.DrK.service.CustomUserDetailService;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component(value = "jwtTokenProvider")
public class JwtTokenProvider {
    
    private String secretKey = "recruit-list";

    private final long tokenValidTime = 60 * 60 * 1000L;

    private CustomUserDetailService userDetailsService;

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(final String userName) { 
        final Claims claims = Jwts.claims().setSubject(userName);
        final Date now = new Date();
        
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(new Date(now.getTime() + tokenValidTime))
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();
    }

    public Authentication getAuthentication(final String token){
        final String userName = userDetailsService.loadUserByUsername(getUserNameByToken(token));
        return new UsernamePasswordAuthenticationToken(userName, "", null);
    }

    public String getUserNameByToken(final String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    public boolean valiDateToken(final String jwtToken) {
        try {
            final Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (final Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}