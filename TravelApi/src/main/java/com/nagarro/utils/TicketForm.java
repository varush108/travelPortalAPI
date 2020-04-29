package com.nagarro.utils;

import java.util.Map;

import javax.persistence.Convert;

public class TicketForm {
	
	private long userid;
	private long typeid;
	@Convert(converter = HashMapConverter.class)
	private Map<String, Object> details;
	

}
