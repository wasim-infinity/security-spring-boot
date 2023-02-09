package com.springboot.security.auth;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil implements Serializable {

	private final long jwtTokenValidity = 1000 * 60 * 60 * 10;

	private final String secretKey = "c0WLB5c7T7YgBuV6gUGlzQpuOFzlyKj2Uv3pzGXKyVliqIUz84iZDYX0q4dY4oLZ";

	public String getUserIdFromToken(String jwtToken) {
		return getClaimFromToken(jwtToken, Claims::getId);
	}
	
	public String extractUsername(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	private Date getExpirationDateFromToken(String jwtToken) {
		return getClaimFromToken(jwtToken, Claims::getExpiration); 
	}


	private <T> T getClaimFromToken(String jwtToken, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(jwtToken);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String jwtToken) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken).getBody();
	}

	private Boolean isTokenExpired(String jwtToken) {
		final Date expiration = getExpirationDateFromToken(jwtToken);
		return expiration.before(new Date());
	}
	
	public String generateToken(String username) {
		HashMap<String, Object> claims = new HashMap<String, Object>();
		return createToken(claims, username);
	}

	public String createToken(HashMap<String, Object> claims, String subject) {
		
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + jwtTokenValidity))
				.signWith(SignatureAlgorithm.HS256, secretKey).compact(); 
	}

	public Boolean validateToken(String jwtToken) {
		return !isTokenExpired(jwtToken);
	}
}
