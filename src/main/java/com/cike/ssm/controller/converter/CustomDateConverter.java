package com.cike.ssm.controller.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

import com.sun.org.apache.regexp.internal.recompile;

public class CustomDateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		try {
			//进行日期转换
			return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").parse(source);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
