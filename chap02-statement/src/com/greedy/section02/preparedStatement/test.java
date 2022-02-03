package com.greedy.section02.preparedStatement;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class test {
	public static void main(String[] args) {
		
		Properties prop = new Properties();
		prop.setProperty("keyString", "valueString");
		
		try {
			prop.storeToXML(new FileOutputStream("src/com/greedy/section02/preparedStatement/employee-qurey.xml"), "");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
