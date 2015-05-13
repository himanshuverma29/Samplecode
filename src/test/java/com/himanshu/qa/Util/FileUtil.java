/*Author: Himanshu Verma
 Project: SampleCodeAutomation
 */
package com.himanshu.qa.Util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.himanshu.qa.Util.FileUtil;

public class FileUtil {

	public static String separator = System.getProperty("file.separator");
	// public static String fileName =
	// "SampleCodeAutomation"+separator+"Constant.cfg";
	public static String fileName = separator + "Constant.cfg";
	public static InputStream input = null;

	public static String getConstantValue(String property) {
		Properties prop = new Properties();

		String value = null;
		String path = null;
		// System.out.println("file input constant.cfg :: "+System.getProperty("user.dir")+separator+fileName);
		try {

			path = System.getProperty("user.dir") + separator
					+ "SampleCodeAutomation" + fileName;
			File f = new File(path);
			if (f.exists()) {

				input = new FileInputStream(path);
			} else {

				input = new FileInputStream(System.getProperty("user.dir")
						+ fileName);
			}

			prop.load(input);
			value = prop.getProperty(property);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return value;
	}

	/*
	 * public static void loadEnvironment(String environment) throws IOException
	 * {
	 * 
	 * String path =
	 * System.getProperty("user.dir")+separator+FileUtil.getConstantValue
	 * ("ProjectName"); System.out.println("path is: "+path); path = path +
	 * separator + FileUtil.getConstantValue("ConfigPath") + separator;
	 * System.out.println("path is: "+path); FileWriter file = new
	 * FileWriter(path + FileUtil.getConstantValue("TestEnvironment"), false);
	 * BufferedWriter define_environment = new BufferedWriter(file);
	 * define_environment.write("environment=" + environment);
	 * define_environment.close(); }
	 */

	public static void loadEnvironment(String environment) throws IOException {

		String path = null;
		path = System.getProperty("user.dir") + separator + "SampleCodeAutomation"+separator
				+ FileUtil.getConstantValue("ConfigPath");
		FileWriter file = null;
		File f = new File(path);
		if (f.exists()) {
			file = new FileWriter(path + separator
					+ FileUtil.getConstantValue("TestEnvironment"), false);
		} else {
			file = new FileWriter(System.getProperty("user.dir") + separator

			+ getConstantValue("ConfigPath") + separator
					+ getConstantValue("TestEnvironment"), false);
		}
		BufferedWriter define_environment = new BufferedWriter(file);
		define_environment.write("environment=" + environment);
		define_environment.close();
	}

	public static String createFile(String fileName) {

		String userHome = System.getProperty("user.dir");

		try {

			//System.out.println(userHome + separator + fileName);
			File file = new File(userHome + separator + fileName);

			if (file.createNewFile()) {
				System.out.println("File is created!");
			} else {
				System.out.println("File already exists.");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}
	
	public FileReader openOrCreateFileToRead(String filePath) throws IOException{
		File file = new File(filePath);
		if(!file.exists()){
			file.createNewFile();
		}
		FileReader fileReader = new FileReader(file.getName());
		return fileReader;
	}
	
	public FileWriter openOrCreateFileToWrite(String filePath, boolean appendModeOrNot) throws IOException{
		File file = new File(filePath);
		if(!file.exists()){
			file.createNewFile();
		}
		FileWriter fileWriter = new FileWriter(file.getName(),appendModeOrNot);
		return fileWriter;
	}
}
