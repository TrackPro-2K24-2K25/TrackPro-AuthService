package org.youcode.trackproauthservice.Util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import org.youcode.trackproauthservice.DTO.AppUserDTO;

import java.util.Date;

@Component
public class JwtUtil {

    private String secretKey = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    private long validityInMilliseconds = 86400000;

    public String generateToken(AppUserDTO user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validityInMilliseconds))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

}
