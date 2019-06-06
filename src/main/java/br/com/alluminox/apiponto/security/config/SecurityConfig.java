package br.com.alluminox.apiponto.security.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.alluminox.apiponto.data.services.FuncionarioService;
import br.com.alluminox.apiponto.security.JwtTokenUtil;
import br.com.alluminox.apiponto.security.config.errors.JwtAccessDeniedHandler;
import br.com.alluminox.apiponto.security.config.errors.JwtAuthenticationEntryPoint;
import br.com.alluminox.apiponto.security.filters.JwtAuthenticationFilter;
import br.com.alluminox.apiponto.security.filters.JwtAuthorizationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and()
			.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/auth/login").permitAll()
			.antMatchers(HttpMethod.POST, "/*/funcionario").permitAll()
			.antMatchers("/*/empresa/**").permitAll()
			.antMatchers("/swagger-ui.html/**").hasRole("DEVELLOPER")
			.antMatchers(HttpMethod.POST, "/*/api/docs/*").hasRole("DEVELLOPER")
			.anyRequest()
			.authenticated()
			.and()
			.addFilter(getAuthenticationFilter())			
			.addFilter(getAuthorizationFilter())
			.exceptionHandling()
				.authenticationEntryPoint(getAuthenticationEntryPoint())
				.accessDeniedHandler(getAccessDebiedHandler())
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		super.configure(http);
	}
	
	// Custom 403
	@Bean
	public AuthenticationEntryPoint getAuthenticationEntryPoint() {
		return new JwtAuthenticationEntryPoint();
	}

	// Custom 401
	@Bean
	public AccessDeniedHandler getAccessDebiedHandler() {
		return new JwtAccessDeniedHandler();
	}


	public JwtAuthenticationFilter getAuthenticationFilter() throws Exception {
		final JwtAuthenticationFilter filter = new JwtAuthenticationFilter(authenticationManager(), this.funcionarioService, this.jwtTokenUtil);
		filter.setFilterProcessesUrl("/auth/login");
		return filter;
	}
	
	public JwtAuthorizationFilter getAuthorizationFilter() throws Exception {
		return new JwtAuthorizationFilter(authenticationManager(), funcionarioService, jwtTokenUtil);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(funcionarioService)
		.passwordEncoder(passwordEncoder);
	}
	
	
	@Bean
	public CorsConfigurationSource corsConfiguration() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST","PUT", "DELETE", "OPTIONS"));
		corsConfiguration.setAllowCredentials(true);
		
		final UrlBasedCorsConfigurationSource urlBase = new UrlBasedCorsConfigurationSource();
		urlBase.registerCorsConfiguration("/**", corsConfiguration);
		
		return urlBase;
	}
}

