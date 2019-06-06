package br.com.alluminox.apiponto.security.config.errors;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alluminox.apiponto.error.ErrorMessage;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setName("Access Denied");
		errorMessage.setStatusCode(403);
		errorMessage.setMessage("Permissão negada, credenciais não econtrada");
		errorMessage.setDevelloperMessage("Você não tem permissão para acessar esse recurso!");
		
		response.addHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter writter = response.getWriter();
		writter.write(new ObjectMapper().writeValueAsString(errorMessage));		
	}

}
