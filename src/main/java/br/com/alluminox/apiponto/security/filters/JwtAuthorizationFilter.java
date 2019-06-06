package br.com.alluminox.apiponto.security.filters;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.transaction.annotation.Transactional;

import br.com.alluminox.apiponto.data.services.FuncionarioService;
import br.com.alluminox.apiponto.io.model.Funcionario;
import br.com.alluminox.apiponto.security.JwtTokenUtil;
import br.com.alluminox.apiponto.security.SecurityProperties;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

	private JwtTokenUtil jwtTokenUtil;
	private FuncionarioService funcionarioService;


	public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
			FuncionarioService funcionarioService,
			JwtTokenUtil jwtTokenUtil) {
		super(authenticationManager);
		this.funcionarioService = funcionarioService;
		this.jwtTokenUtil = jwtTokenUtil;
	}
	
	@Override
	@Transactional(readOnly = true)
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String token = getPrefixToken(request);
		
		if(token == null || !token.startsWith(SecurityProperties.TOKEN_PREFIX))  {
			chain.doFilter(request, response);
			return;
		}
		
		SecurityContextHolder.getContext().setAuthentication(getAuthenticationToken(request));
		chain.doFilter(request, response);
	}
	
	
	private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {
		String token = getPrefixToken(request).replace(SecurityProperties.TOKEN_PREFIX, "");		
		String email = jwtTokenUtil.getUsernameFromToken(token);		
				
		Funcionario funcionario = this.funcionarioService.findByEmail(email).get();
		List<GrantedAuthority> authorties = Arrays.asList(new SimpleGrantedAuthority(funcionario.getPerfil().name()));
		
		return funcionario != null ? 
					new UsernamePasswordAuthenticationToken(funcionario, 
					funcionario.getSenha() ,
					authorties) : null;
	}

	private String getPrefixToken(HttpServletRequest request) {
		return request.getHeader(SecurityProperties.HEADER_PREFIX);
	}
}
