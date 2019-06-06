package br.com.alluminox.apiponto.security;

import java.util.concurrent.TimeUnit;

public class SecurityProperties {
	
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_PREFIX = "Authorization";
	public static final Long EXPIRATION_TIME = 432000000L; // 5 DIAS
	public static final String SECRET = "712$i0usd12@1a3md9dad1209";
	
	public static void main(String[] args) {
		System.out.println(TimeUnit.MILLISECONDS.convert(5, TimeUnit.DAYS));
	}
}
