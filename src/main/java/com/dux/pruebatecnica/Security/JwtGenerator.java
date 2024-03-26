package com.dux.pruebatecnica.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtGenerator {

    public String generarToken(Authentication authentication){
        String username = authentication.getName();
        Date actualdate = new Date();
        Date expirationDate = new Date(actualdate.getTime()+ ConstanteSeguridad.JWT_EXPIRATION_TOKEN);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512,ConstanteSeguridad.JWT_FIRMA)
                .compact();
        return token;

}
public String obtenerUsernameDeJwt(String token){
    Claims claims = Jwts.parser()
            .setSigningKey(ConstanteSeguridad.JWT_FIRMA)
            .parseClaimsJws(token)
            .getBody();
    return claims.getSubject();
}

public Boolean validarToken(String token){
        try{
            Jwts.parser().setSigningKey(ConstanteSeguridad.JWT_FIRMA).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            throw new AuthenticationCredentialsNotFoundException("jwt expiro ");
        }
}


}