package ar.edu.unq.desapp.grupoK.backenddesappapi.service;

import ar.edu.unq.desapp.grupoK.backenddesappapi.service.serviceLevelExceptions.TokenValidationException;
import io.jsonwebtoken.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class JwtToken {
    private static final SignatureAlgorithm sA =SignatureAlgorithm.HS256;
    private static final String secret= "resenia";

    static public String getTokenFor(String subject){

        Instant ins = Instant.now();
        return Jwts.builder().setSubject(subject)
                .setIssuedAt(Date.from(ins))
                .setExpiration(java.util.Date.from(ins.plus(2, ChronoUnit.HOURS)))
                .signWith(sA,secret)
                .compact();
    }
    static public void isValidToken(String token, String tokenOwner) throws TokenValidationException {
        try {
            String jotName = getTokenName(token);
            if (!jotName.equals(tokenOwner)) {
                throw new TokenValidationException("No tiene permiso o session caducada");

            }
            return ;
        }
        catch ( ExpiredJwtException| UnsupportedJwtException| MalformedJwtException| SignatureException | IllegalArgumentException e){

            throw new TokenValidationException("No tiene permiso o session caducada " + e.getMessage());
        }
    }
    static private String getTokenName(String token){
        //System.out.println("algo: " + Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("sub",String.class));
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("sub",String.class);
    }
}
