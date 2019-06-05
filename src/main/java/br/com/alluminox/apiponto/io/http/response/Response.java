package br.com.alluminox.apiponto.io.http.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Response<T> implements Serializable  {
	private static final long serialVersionUID = 1L;

	private T data;
	private List<String> validationErrors;
	
	public Response() {
		data = null;
		this.validationErrors = new ArrayList<>();
	}
	
	public Response(T arg0) {
		this.data = arg0;
		this.validationErrors = new ArrayList<>();
	}
	

	public Response(T arg0, List<String> arg1) {
		this(arg0);
		this.validationErrors = arg1;
	}
	
	public void addError(String validationError) {
		if(!validationError.contains(validationError))
			this.validationErrors.add(validationError);
	}
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public List<String> getValidationErrors() {
		return validationErrors;
	}
	public void setValidationErrors(List<String> validationErrors) {
		this.validationErrors = validationErrors;
	}
	
}
