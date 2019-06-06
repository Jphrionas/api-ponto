package br.com.alluminox.apiponto.security.config.errors;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alluminox.apiponto.error.ErrorMessage;

public class JwtAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {

		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setStatusCode(HttpStatus.UNAUTHORIZED.value());
		errorMessage.setMessage("Permissão negada, credenciais não econtrada");
		errorMessage.setDevelloperMessage("Você não tem permissão para acessar esse recurso!");
		errorMessage.setName("Unauthorized");
		
		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		if(auth != null) {
			User user = (User) auth.getPrincipal();
			errorMessage.setMessage(String.format("O usuário , %s, não possui acesso a este recurso!", user.getUsername()));
		}
		PrintWriter writter = response.getWriter();
		response.addHeader("Content-Type", "application/json;charset=utf-8");
		writter.write(new ObjectMapper().writeValueAsString(errorMessage));
	}
	
	

}
