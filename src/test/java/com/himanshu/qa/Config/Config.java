/*Author: Himanshu Verma
 Project: SampleCodeAutomation
 */
package com.himanshu.qa.Config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.testng.*;

import com.himanshu.qa.Util.FileUtil;

public class Config {

	private static Config instance = null;
	private Properties globalConfigs;
	private Properties domainConfigs;
	private String domain = "debug";
	private String browser = "";
	public static String ext = ".cfg";

	private static void loadConfigFile(String configLocation) {

		String globalConfigFile = configLocation + FileUtil.getConstantValue("TestEnvironment");
		instance = new Config();
		instance.globalConfigs = new Properties();
		try {
			instance.globalConfigs.load(new FileInputStream(globalConfigFile));
			if (instance.globalConfigs.get("environment") != null)
				instance.domain = instance.globalConfigs.get("environment")
						.toString();
			System.out.println("Environment file Name:: "+instance.globalConfigs.get("environment")
						.toString());
			String domainConfigFile = configLocation + instance.domain + ext;
			System.out.println("domainConfigFile Path---->"+domainConfigFile);
			instance.domainConfigs = new Properties();
			instance.domainConfigs.load(new FileInputStream(domainConfigFile));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failure in loading config file");
		}
	}

	public static Config getInstance() throws Exception {

		return getInstance(getConfigLocation());
	}

	public static Config getInstance(String configLocation) throws Exception {
		if (instance == null){
			System.out.println("configLocation file --->"+configLocation);
			loadConfigFile(configLocation);
		}
		return instance;
	}

	public String getConfig(String key) throws Exception {

		if (instance == null)
			throw new RuntimeException("Initialize AppConfig");
		Object value = null;

		value = domainConfigs.get(key);
		if (value == null) {
			value = globalConfigs.get(key);
		}
		if (value != null)
			return value.toString();
		else
			return null;
	}

	public String getDomain() {
		return domain;
	}

	public String getBrowser() {
		return browser;
	}


	public static String getConfigLocation() {
		//src/main/java/com/snapdeal/qa/Config
		String path = System.getProperty("user.dir");
	//	path = path +FileUtil.separator+FileUtil.getConstantValue("ConfigPath")+FileUtil.separator;
		path = path +FileUtil.separator+"UIAutomationWap"+FileUtil.separator+FileUtil.getConstantValue("ConfigPath")+FileUtil.separator;
		File f = new File(path);
		if (f.exists()) {
		return path;

	} else{
		
		path = System.getProperty("user.dir") +FileUtil.separator+FileUtil.getConstantValue("ConfigPath")+FileUtil.separator;
         return path;
	}
		
	
}


	

}
