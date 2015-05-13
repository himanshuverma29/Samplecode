/*Author: Himanshu Verma
 Project: SampleCodeAutomation
 */
package com.himanshu.qa.TestBase;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.himanshu.qa.Util.FileUtil;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.LoggerContextListener;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.LifeCycle;

public class LoggerStartupListener extends ContextAwareBase implements LoggerContextListener, LifeCycle {

	static DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	static Calendar cal = Calendar.getInstance();
    private static final String DEFAULT_LOG_FILE=FileUtil.createFile("LogFile_"+ dateFormat.format(cal.getTime()));
    private boolean started = false;

    public void start() {
        if (started) return;
 
      //  System.setProperty("LOG_FILE", value)
      //  System.setProperty("log.file", DEFAULT_LOG_FILE);
        String userHome = System.getProperty("user.home"); 

        String logFile = System.getProperty("log.file"); // log.file is our custom jvm parameter to change log file name dynamicly if needed

        System.out.println(userHome + " " + logFile);
      //  logFile = (logFile != null && logFile.length() > 0) ? logFile : DEFAULT_LOG_FILE;
        logFile=DEFAULT_LOG_FILE;
        Context context = getContext();

        context.putProperty("MY_HOME", userHome);
        context.putProperty("LOG_FILE", logFile);

        started = true;
    }

    public void stop() {
    }

    public boolean isStarted() {
        return started;
    }

    public boolean isResetResistant() {
        return true;
    }

    public void onStart(LoggerContext context) {
    }

    public void onReset(LoggerContext context) {
    }

    public void onStop(LoggerContext context) {
    }

    public void onLevelChange(Logger logger, Level level) {
    }
   
}   
