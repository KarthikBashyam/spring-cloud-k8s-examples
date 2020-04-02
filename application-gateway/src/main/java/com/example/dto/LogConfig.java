package com.example.dto;

public class LogConfig {

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private String message;

	public LogConfig(String message) {
		super();
		this.message = message;
	}

}
