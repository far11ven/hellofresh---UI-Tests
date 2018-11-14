package com.hellofresh.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/*
 * This class provides instance of ConfigReader for reading values from "config.properties"
*/
public class ConfigReader {

	private static LogUtils LOGGER;
	private static FileInputStream fis = null;
	private static Properties pro = new Properties();
	private static String filePath = System.getProperty("user.dir") + "/src/test/resources/" + "config.properties";

	public ConfigReader( Class<?> ClassName) {
		LOGGER = new LogUtils(ClassName);
	}

	/*
	 * This method provides reading values from "config.properties", takes one parameter "propName" whose value needs to be returned
	*/
	public static String getProperty(String propName) {

		try {
			LOGGER.info("FileReader searching for file at = " + filePath);
			fis = new FileInputStream(new File(filePath));
		}
		catch(FileNotFoundException e){

			LOGGER.error("FileReader could not find the requested file...!!");

		}

		try {
			pro.load(fis);
		}
		catch(IOException e){

			LOGGER.error("FileReader could not load the requested file...!!");

		}

		LOGGER.info("Retrieving value for property [" + propName +"] as :" + pro.getProperty(propName));
		
		return pro.getProperty(propName);
	}


}