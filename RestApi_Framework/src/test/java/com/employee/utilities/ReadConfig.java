package com.employee.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties pro;
	//Constructor
	public ReadConfig() {
		String path = System.getProperty("user.dir");
		File src=new File(path+"\\Configuartion\\config.properties");
		
		try {
			FileInputStream fis=new FileInputStream(src);
			pro=new Properties();
			pro.load(fis);
		}
		catch(Exception e) {
			System.out.println("Exception is :"+e);
		}
	}
	
	public String get_applicatio_url() {
		String url=pro.getProperty("baseURL");
		return url;
	}
//	public static void main(String[] args) {
//		ReadConfig config=new ReadConfig();
//		String url=config.get_applicatio_url();
//		System.out.println("Url is:"+ url);
//	}
}
