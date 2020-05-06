package com.remd.spring.enums.errors;

public enum ErrorImagePath {
	
	ERROR404("missing-error.jpg"),
	ERROR500("sad-cat-error.jpg");
	
	private String url;

	private ErrorImagePath(String url) {
		this.url = url;
	}

	public String getErrorImagePath() {
		return url;
	}
}
