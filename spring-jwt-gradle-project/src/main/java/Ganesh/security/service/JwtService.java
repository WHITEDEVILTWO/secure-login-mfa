package Ganesh.security.service;

//import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;


import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class JwtService {
	
	private final Key KEY=Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private final long expiretime=1000 * 60 *30;

	public String generateToken(String username) {
		LocalDateTime date=LocalDateTime.now();
		System.err.println("Secret key : "+ KEY.getAlgorithm().toCharArray().toString());
		String token = Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+expiretime))
				.signWith(KEY)
				.compact(); // it will compress the key into Header+payload+signature
		System.err.println("Key: "+KEY);
		System.err.println("JWT token : "+token);
		System.err.println("issed date: "+date);
		System.err.println("Expire at :"+System.currentTimeMillis()+expiretime);
		return token;
	}
	
	public String extractuser(String token) {
		return  Jwts.parserBuilder().setSigningKey(KEY).build()
				.parseClaimsJws(token).getBody().getSubject();
	}
	
	public boolean isTokenValid(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token); //throws exception if any tampering or signature/expiry not matches 
			return true;
		}catch (JwtException e) {
			// TODO: handle exception
			return false;
		}
	}

	@Override
	public String toString() {
		return "JwtService [key=" + KEY + "]";
	}
}
