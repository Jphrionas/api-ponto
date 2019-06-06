package br.com.alluminox.apiponto.security;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable  {
	private static final long serialVersionUID = 1L;

	private static final String CLAIM_KEY_USERNAME = "sub";
	private static final String CLAIM_KEY_ROLE = "role";
	private static final String CLAIM_KEY_CREATED = "created";
	
	// Get Username 
	public String getUsernameFromToken(String token) {
		Claims claims = getClaimsFromToken(token);
		return claims.getSubject();
	}
	
	// Get  
	public Date getExpirationTomeFromToken(String token) {
		Claims claims = getClaimsFromToken(token);
		return claims.getExpiration();
	}
	
	public String refreshToken(String token) {
		Claims claims = getClaimsFromToken(token);
		claims.put(CLAIM_KEY_CREATED, new Date());
		return generateToken(claims);
	}
	
	private String generateToken(Map<String, Object> claims) {
		return Jwts.builder().setClaims(claims)
				.setExpiration(generateExpirationTime())
				.signWith(SignatureAlgorithm.HS512, SecurityProperties.SECRET)
				.compact();
	}

	public String generateToken(User user) {
		Map<String, Object> claims = new HashMap<>();
		claims.put(CLAIM_KEY_USERNAME, user.getUsername());
		user.getAuthorities().forEach(authority -> claims.put(CLAIM_KEY_ROLE, authority.getAuthority()));
		claims.put(CLAIM_KEY_CREATED, new Date());
		
		return generateToken(claims);
	}

	
	public boolean tokenisValid(String token) {
		return !tokenHasExpired(token);
	}
	
	private boolean tokenHasExpired(String token) {
		Date expirationTomeFromToken = this.getExpirationTomeFromToken(token);
		return new Date().after(expirationTomeFromToken);
	}

	public Date generateExpirationTime() {
		return new Date(System.currentTimeMillis() + SecurityProperties.EXPIRATION_TIME);
	}
	
	private Claims getClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(SecurityProperties.SECRET).parseClaimsJws(token).getBody();
	}
}
