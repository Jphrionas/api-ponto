package br.com.alluminox.apiponto.error;

import java.io.Serializable;

public class ErrorMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private int statusCode;
	private String message;
	private String develloperMessage;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDevelloperMessage() {
		return develloperMessage;
	}
	public void setDevelloperMessage(String develloperMessage) {
		this.develloperMessage = develloperMessage;
	}
	
	
	
	
}
