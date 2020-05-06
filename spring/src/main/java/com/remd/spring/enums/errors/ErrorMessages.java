package com.remd.spring.enums.errors;

public enum ErrorMessages {
	ERROR404("Error! The resource you requested could not be located"),
	ERROR500("Error! Something went wrong.");
	
	private String msg;
	
	private ErrorMessages(String msg) {
		this.msg = msg;
	}
	public String getErrorMessage() {
		return msg;
	}
}
