package com.example.first.lang.utils;

import com.example.first.lang.exception.DataParseException;
import com.example.first.lang.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;
public class LongUtils {
	private static Logger logger = LoggerFactory.getLogger(LongUtils.class);
	
	public LongUtils() {
	}
	
	public static Long parseLong(Object value, Long defaultValue) {
		if(value == null || StringUtils.isBlank(value.toString())) {
			return defaultValue;
		}
		
		if(value instanceof Long) {
			return (Long)value;
		}
		
		Long returnValue = 0L;
		if(value.getClass().isAssignableFrom(Number.class)) {
			returnValue = ((Number)value).longValue();
		}
		
		try {
			returnValue = Long.parseLong(value.toString());
		} catch(Exception exp) {
			logger.error("Long类型转换失败, 原值:" + value, exp);
			throw new DataParseException("类型转换失败");
		}
		
		return returnValue;
	}
	
	public static Long parseLong(Object value, Long defaultValue, String tips) {
		try {
			return parseLong(value, defaultValue);
		} catch(DataParseException exp) {
			throw new ServiceException(tips, exp);
		}
	}
}
