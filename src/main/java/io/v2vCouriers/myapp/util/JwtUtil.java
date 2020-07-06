package io.v2vCouriers.myapp.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

@Service
public class JwtUtil {

	SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256); //or HS384 or HS512
	byte[] key1 = key.getEncoded();
	String base64Key = Encoders.BASE64.encode(key1);
    

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    @SuppressWarnings("deprecation")
	private String createToken(Map<String, Object> claims, String subject) {

    	return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, base64Key).compact();
       
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        
    	if (token != null)
    	{
    		return true;
    	}
    	else {
			return false;
		}
    }
}
