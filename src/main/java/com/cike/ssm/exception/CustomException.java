package com.cike.ssm.exception;
/**
 * 自定义异常
 * @author CIKE
 *
 */
public class CustomException extends Exception{
	private String message;

	public CustomException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
