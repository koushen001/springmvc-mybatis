package com.cike.ssm.controller.converter;

import org.springframework.core.convert.converter.Converter;

public class StringtrimConverter implements Converter<String, String> {

	@Override
	public String convert(String source) {
		try {
			// 去掉字符串两边空格
			if (source != null) {
				source = source.trim();
				if (source.equals("")) {
					return null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return source;
	}

}
