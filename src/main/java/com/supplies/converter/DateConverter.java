package com.supplies.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * 自定义类型转换器   注意:日期变量必须包含在某个实体类中
 * @author pata
 *
 */
@Component
public class DateConverter implements Converter<String, Date> {
	
	private static final SimpleDateFormat[] sdf = new SimpleDateFormat[4];
	
	static{
		sdf[0] = new SimpleDateFormat("yyyy-MM-dd");
		sdf[1] = new SimpleDateFormat("yyyy/MM/dd");
		sdf[2] = new SimpleDateFormat("yyyy年MM月dd日");
	}

	@Override
	public Date convert(String arg0) {
		System.out.println("日期转换中" + arg0);
		for (SimpleDateFormat s : sdf) {
			try {
				return s.parse(arg0);
			} catch (ParseException e) {
				continue;
			}
		}
		return null;
	}
}
