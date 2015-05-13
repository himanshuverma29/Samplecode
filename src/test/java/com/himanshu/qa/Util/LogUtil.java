/*Author: Himanshu Verma
 Project: SampleCodeAutomation
 */
package com.himanshu.qa.Util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.himanshu.qa.Util.FileUtil;

public class LogUtil {
	
	public static Logger createLogger(Class classname, String filename,
			boolean append) {

		SimpleDateFormat date_format = new SimpleDateFormat("dd-MM-yyyy");
		String date = date_format.format(new Date());
		filename = filename + "." + date;

		Logger LOGGER = null;
		try {
			LOGGER = Logger.getLogger(classname.getName());

			LOGGER.setLevel(Level.INFO);
			FileHandler handler = new FileHandler(
					System.getProperty("user.dir") + FileUtil.separator+FileUtil.getConstantValue("LogsPath")+FileUtil.separator + filename
							+ ".txt", append);
			handler.setFormatter(new SimpleFormatter());
			LOGGER.addHandler(handler);
			LOGGER.setUseParentHandlers(false);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return LOGGER;

	}

}
