package com.supplies.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomJob {
	
	public void execute(){
		System.out.println("当前时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}
}
