package org.sharath.messenger.exceptions;

public class DataNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 134123L;
	public DataNotFoundException(String message){
		super(message);
	}
}
