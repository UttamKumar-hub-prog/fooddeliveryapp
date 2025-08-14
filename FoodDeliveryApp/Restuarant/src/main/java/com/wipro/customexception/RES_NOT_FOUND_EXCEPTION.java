package com.wipro.customexception;


public class RES_NOT_FOUND_EXCEPTION extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public RES_NOT_FOUND_EXCEPTION(String message) {
		super(message);
	}
}
