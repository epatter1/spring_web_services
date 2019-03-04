package com.samplespring.rest.webservices.restfulwebservices.exception;

import java.util.Date;

import org.springframework.validation.BindingResult;

public class ExceptionResponse {
	//NOTE: the structure/format for these responses should be
	//consistent across the organization
	//so everyone can use it!
	
	//timestamp
	private Date timestamp;
	//message
	private String message;
	//detail
	private String details;
	public ExceptionResponse(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	
	//created this for the BAD_REQUEST ResponseEntity method - can be deleted if necessary
	public ExceptionResponse(Date timestamp2, String message2, BindingResult bindingResult) {
		// TODO Auto-generated constructor stub
	}

	public Date getTimestamp() {
		return timestamp;
	}
	public String getMessage() {
		return message;
	}
	public String getDetails() {
		return details;
	}
	
	
}
