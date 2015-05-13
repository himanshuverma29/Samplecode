/*Author: Himanshu Verma
 Project: SampleCodeAutomation
 */

package com.himanshu.qa.Util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import com.himanshu.qa.Config.Config;

public class WebServerLog {
	private Connection connection = null;
	private Session session = null;
	public Config config;
	private String readLogUser,serverEnv,serverIP;
	private String startTime,endTime;
	Logger logger = LoggerFactory.getLogger(WebServerLog.class);
	public String genrateServerLog(String startTime ,String endTime){
		String fileName = null;
		try{
			config = config.getInstance();
			//serverIP = config.getConfig("serverIP");
			readLogUser = config.getConfig("readLogUser");
			serverEnv = config.getConfig("serverEnv");
			Date now = new Date(); // java.util.Date, NOT java.sql.Date or java.sql.Timestamp!
			String dateFormat = new SimpleDateFormat("yyyy-MM-dd").format(now);
			//String command ="grep -5" + " \"2015 ["+startTime+"-"+endTime+"]:[0-9][0-9]:[0-9][0-9]\" " + "/usr/local/mobile-tomcat/logs/localhost.2015-04-30.log";
			String command ="grep -5" + " \"2015 ["+startTime+"-"+endTime+"]:[0-9][0-9]:[0-9][0-9]\" " + "/usr/local/mobile-tomcat/logs/localhost." + dateFormat + ".log";
			StringBuffer sb  = executeCommand(command);
			fileName  = writeLogFile(sb);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return fileName;
	}
	public String  writeLogFile(StringBuffer sb){
		String fileName = null;
		try {
			
			logger.info("Buffer size of file  is::"+sb.length());
			if(sb.length() > 0){
			DateFormat format=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
			 Date date=new Date();
			String physicalPath =System.getProperty("user.dir")+"/tomcatlogs/";
			File fileDir = new File(physicalPath);
			if(!fileDir.exists()){
				logger.info("Create new File DIR in worksapce::");
				fileDir.mkdir();
			}
			logger.info("Server log File genration path is :: "+physicalPath);
			 fileName = serverEnv+format.format(date)+".txt";
			File file = new File(physicalPath+fileName);
			// if file doesnt exists, then create it
			if (!file.exists()) {
				logger.info("New Server Log File Creating");
				file.createNewFile();
			}
 			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(sb.toString());
			bw.close();
 			System.out.println("Done");
			}
			else{
				logger.info("--Log not found on tomcar server local host--");
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}
	
	public StringBuffer executeCommand(String command) throws IOException {
        StringBuffer buffer = new StringBuffer();
        opensshConnection();
        openSession();
        try {
            session.execCommand(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream stdout = new StreamGobbler(session.getStdout());
        BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
        Scanner scanner = new Scanner(stdout);
        while (scanner.hasNext()) {
            String line = null;
            line = scanner.nextLine();
            //logger.info(line);
			if (line == null){
				System.out.println("line end");
				//System.exit( 0 );
			    break;
			    }
			buffer.append(line).append("\n");
        }
        closeSession();
        closeConnection();
        return buffer;
    }
	
	public Connection opensshConnection(){
		try {
			System.out.println("serverIP for Loging is ::"+APIConfiguration.serverIP);
			connection = new Connection(APIConfiguration.serverIP, 22);
			connection.connect();
			String path = System.getProperty("user.dir");
			path=path+"/resources/";
			File key = new File(path + "/" + "himanshu.pem");
			String  readLog= readLogUser;
			//String  readLog= "dd9127";
			boolean isAuthenticated = connection.authenticateWithPublicKey(
			        readLog, key, null);
			if (isAuthenticated == false) {
				System.out.println("Problem in authentication ");
				throw new IOException("Authentication failed.");
			}
			else {
				return connection;
			}
			     
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		return connection;
		
	}
	
	private void openSession() {
		try {
			session = connection.openSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void closeSession() {
		System.out.println("Session is closed");

		session.close();

	}
	
	public void closeConnection() {
		System.out.println("Connection is closed");
		connection.close();

	}
	
	/*public static void main(String[] args) {
		WebServerLog log = new WebServerLog();
		log.genrateServerLog("2", "4");
	}*/
	@BeforeSuite()
	public void beforeSuite() {
		 System.out.println("Calling Before Suit----->");
    	 Date now = new Date(); 
         String dateFormat = new SimpleDateFormat("yyyy-MM-dd").format(now);
         DateFormat dateFormat1 = new SimpleDateFormat("h");
         Calendar cal = Calendar.getInstance();
         startTime = dateFormat1.format(cal.getTime());
         cal.add(Calendar.HOUR_OF_DAY, 2);
         endTime = dateFormat1.format(cal.getTime());
       
	}
 
	@AfterSuite
	public void afterSuite() {
		System.out.println("Calling After Suit----->");
		String fileName = genrateServerLog(startTime, endTime);
		System.out.println("File Name ------->"+fileName);
		SendEmail.serverLogSendMail("himanshu_verma_29@yahoo.com",fileName);
	}

}
