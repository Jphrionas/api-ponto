package br.com.alluminox.apiponto.error;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourcesErrors implements Serializable {
	private static final long serialVersionUID = 1L;

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> runtimeErrorExceptionHandler(RuntimeException ex) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setName("Bad Request");
		errorMessage.setMessage(ex.getMessage());
		errorMessage.setStatusCode(HttpStatus.BAD_REQUEST.value());
		errorMessage.setDevelloperMessage(ex.getLocalizedMessage());
		
		return ResponseEntity.badRequest().body(errorMessage);
	}
}
