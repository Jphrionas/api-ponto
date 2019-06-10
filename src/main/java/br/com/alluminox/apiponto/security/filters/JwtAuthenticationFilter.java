package br.com.alluminox.apiponto.security.filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.alluminox.apiponto.data.services.FuncionarioService;
import br.com.alluminox.apiponto.io.model.Funcionario;
import br.com.alluminox.apiponto.security.JwtTokenUtil;
import br.com.alluminox.apiponto.security.SecurityProperties;
import br.com.alluminox.apiponto.security.model.LoginFormModel;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	private FuncionarioService funcionarioService;
	private JwtTokenUtil jwtTokenUtil;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, 
			FuncionarioService funcionarioService, JwtTokenUtil jwtTokenUtil) {
		this.authenticationManager = authenticationManager;
		this.funcionarioService = funcionarioService;
		this.jwtTokenUtil = jwtTokenUtil;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			@Valid LoginFormModel loginFormModel = new ObjectMapper().readValue(request.getInputStream(), LoginFormModel.class);
			UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(loginFormModel.getUsername(), loginFormModel.getPassword());
			return authenticationManager.authenticate(userToken);
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Username/Password is inválid!");
		}
	}
	
	@Override
	@Transactional
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		// Pego o usuario que foi autenticado pelo "attempAuthentication" ele devolve um User
		User user = (User) authResult.getPrincipal();
		
		// Busco meu funcionario no banco para verificar o token
		Funcionario funcionario = this.funcionarioService.findByEmail(user.getUsername()).get();
		
		if(!tokenAndExpirationTimeIsValid(funcionario.getToken(), funcionario.getExpirationTime())) {
			String token = jwtTokenUtil.generateToken(user);
			funcionario.setToken(token);
			funcionario.setExpirationTime(jwtTokenUtil.getExpirationTomeFromToken(token).getTime());
			this.funcionarioService.updateToken(funcionario);
		}
		
	
		PrintWriter reponseWriter = response.getWriter();
		reponseWriter.write(generatedTokenResponse(funcionario));
		response.addHeader("Content-Type", "application/json;charset=utf-8");
		response.addHeader(SecurityProperties.HEADER_PREFIX, prepareToken(funcionario.getToken()));
	}
	
	protected boolean tokenAndExpirationTimeIsValid(String token, Long expirationTime) {
		Boolean tokenValid = token != null && !token.isEmpty();
		Boolean expirationTimeValid = expirationTime != null && new Date().before(new Date(expirationTime));
		return tokenValid && expirationTimeValid;
	}
	
	protected String generatedTokenResponse(Funcionario funcionario) {
		try {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode()
			.put("publicId", funcionario.getPublicId())
			.put("expirationTime", funcionario.getExpirationTime())
			.put("token", funcionario.getToken())
			.put("role", funcionario.getPerfil().name())
			.put("empresa", funcionario.getEmpresa().getCnpj());
			return mapper.writeValueAsString(node);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	protected String prepareToken(String token )  {
		return SecurityProperties.TOKEN_PREFIX + token;
	}
	
}

