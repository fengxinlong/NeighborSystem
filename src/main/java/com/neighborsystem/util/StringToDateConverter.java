package com.neighborsystem.util;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
* 把字符串转换成日期
*
* */
public class StringToDateConverter implements Converter<String,Date> {


	/*
	* String source
	* */
	@Override
	public Date convert(String s) {
		if(s==null){
			throw new RuntimeException("请传入数据");
		}else{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

			try {
				Date date = df.parse(s);
				return date;
			} catch (Exception e) {
				throw new RuntimeException("数据类型转换出现错误");
			}


		}
	}
}
