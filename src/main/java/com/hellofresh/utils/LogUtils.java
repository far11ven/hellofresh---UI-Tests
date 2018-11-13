package com.hellofresh.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.hellofresh.utils.TestFailedException;

public class LogUtils {

	private Logger logger;
	private String logFilePath;
	private String fileName;

	public LogUtils(Class<?> ClassName) {

		logger = Logger.getLogger(ClassName);
		
		
		String currDate = new SimpleDateFormat("dd MMMM yyyy").format(Calendar.getInstance().getTime());
		new File(System.getProperty("user.dir") + "/logs/Log - " + currDate).mkdirs();
		
		logFilePath = System.getProperty("user.dir") + "/logs/Log - " + currDate;

		String currTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		fileName  = "Test Run - " + currTime.replaceAll(":", "-") +".log";

	}

	public void writeLog(String type, String message){

		Calendar cal = Calendar.getInstance();
		Date currentTime = cal.getTime();

		switch(type)
		{

		case "ERROR" : 
			Logger.getRootLogger().setLevel(Level.ERROR);
			BasicConfigurator.configure();
			logger.error(type + " " + currentTime + ": " + message);
			writeToLogFile(type + " " + currentTime + ": " + message);
			break;

		case "INFO" : 
			Logger.getRootLogger().setLevel(Level.INFO);
			BasicConfigurator.configure();
			logger.info(type + " " + currentTime + ": " + message);
			writeToLogFile(type + " " + currentTime + ": " + message);
			break;

		case "PASS" : 
			Logger.getRootLogger().setLevel(Level.INFO);
			BasicConfigurator.configure();
			logger.info(type + " " + currentTime + ": " + message);
			writeToLogFile(type + " " + currentTime + ": " + message);
			break;

		case "FAIL" : 
			Logger.getRootLogger().setLevel(Level.ERROR);
			BasicConfigurator.configure();
			logger.error(type + " " + currentTime + ": " + message);
			writeToLogFile(type + " " + currentTime + ": " + message);
			writeToLogFile("Test step failed..");
			throw new TestFailedException("Test step failed..");

		default:
			Logger.getRootLogger().setLevel(Level.INFO);
			BasicConfigurator.configure();
			logger.info(type + " " + currentTime + ": " + message);
			writeToLogFile(type + " " + currentTime + ": " + message);
			break;
		}


	}
	
	public void error(String message){

		Calendar cal = Calendar.getInstance();
		Date currentTime = cal.getTime();

		Logger.getRootLogger().setLevel(Level.ERROR);
		BasicConfigurator.configure();
		logger.error("ERROR" + " " + currentTime + ": " + message);
		writeToLogFile("ERROR" + " " + currentTime + ": " + message);

	}
	
	public void info(String message){

		Calendar cal = Calendar.getInstance();
		Date currentTime = cal.getTime();

		Logger.getRootLogger().setLevel(Level.INFO);
		BasicConfigurator.configure();
		logger.error("INFO" + " " + currentTime + ": " + message);
		writeToLogFile("INFO" + " " + currentTime + ": " + message);

	}
	
	public void warn(String message){

		Calendar cal = Calendar.getInstance();
		Date currentTime = cal.getTime();

		Logger.getRootLogger().setLevel(Level.WARN);
		BasicConfigurator.configure();
		logger.error("WARN" + " " + currentTime + ": " + message);
		writeToLogFile("WARN" + " " + currentTime + ": " + message);

	}
	
	public void pass(String message){

		Calendar cal = Calendar.getInstance();
		Date currentTime = cal.getTime();

		Logger.getRootLogger().setLevel(Level.INFO);
		BasicConfigurator.configure();
		logger.error("PASS" + " " + currentTime + ": " + message);
		writeToLogFile("PASS" + " " + currentTime + ": " + message);

	}
	
	public void fail(String message){

		Calendar cal = Calendar.getInstance();
		Date currentTime = cal.getTime();

		Logger.getRootLogger().setLevel(Level.ERROR);
		BasicConfigurator.configure();
		logger.error("FAIL" + " " + currentTime + ": " + message);
		writeToLogFile("FAIL" + " " + currentTime + ": " + message);
		throw new TestFailedException("Test step failed..");

	}


	public void writeToLogFile(String content){

		FileWriter fw = null;
		BufferedWriter bw = null;

		try {
			fw = new FileWriter(logFilePath + "/" + fileName, true);
			bw = new BufferedWriter(fw);
			bw.newLine();
			bw.write(content);
			bw.flush();
			fw.close();
			bw.close();
		}
		catch(IOException e) {

			Logger.getRootLogger().setLevel(Level.ERROR);
			BasicConfigurator.configure();
			logger.error("ERROR" +  " " + "Can't write to Log File, IOException occurred!!!!!!!!!!!!!!!!! \n");
			e.printStackTrace();


		}

	}



}
