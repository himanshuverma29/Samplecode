/*Author: Himanshu Verma
 Project: SampleCodeAutomation
 */
package com.himanshu.qa.Util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.himanshu.qa.Util.FileUtil;
import com.himanshu.qa.Util.SSHManager;
import com.himanshu.qa.Util.WaitUtil;

import com.himanshu.qa.Config.Config;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.LocalPortForwarder;

// public static final Logger LOG = createLogger(SSHManager.class,
// "sshmanager", true);

public class SSHManager
{
	
private static Connection connection = null;
private static List<LocalPortForwarder> lpf = null;

static Config config;
 
static{
	try {
		config	=	Config.getInstance();
	} catch (Exception e) {
		e.printStackTrace();
	}
}

private void ssh(String sship,String sshusername) throws Exception {
	try {
		connection = new Connection(sship, 22);
		connection.connect();
		String path = System.getProperty("user.dir")+ FileUtil.separator+FileUtil.getConstantValue("PrivateKey");
		File key = new File(path);
		boolean isAuthenticated = connection.authenticateWithPublicKey(
				sshusername, key, null);
		if (isAuthenticated == false) {
			throw new IOException("Authentication failed.");
		}
        System.out.println("SSH connection created successfully !!!");
	} catch (IOException e) {
		e.printStackTrace();
	}

}

public static void closeConnection() {
	connection.close();
	System.out.println("SSH connection closed !!!");

}



private void forwardLocalPort(Integer localport, String remotehost,
		Integer remoteport) {
	try {
		LocalPortForwarder temp = connection.createLocalPortForwarder(
				localport, remotehost, remoteport);
		lpf.add(temp);
	} catch (IOException e) {
		//e.printStackTrace();

	}
	WaitUtil.sleep(5000);
}




private static void myStart(String sship,String user,String dbip,int forwardPort) throws Exception
{
	System.out.println("SSH IP is -->" +sship+ "userName--> "+user+ "DBIP-->"+dbip+ "forwardPort--->"+forwardPort);
	SSHManager manager = new SSHManager();
	lpf = new ArrayList<LocalPortForwarder>();
	manager.ssh(sship,user);		
	manager.forwardLocalPort(forwardPort,	dbip , 3306);
}

public static void createConnection() throws NumberFormatException, Exception
{
	myStart(APIConfiguration.SSHIP, config.getConfig("SSHUsername"), config.getConfig("DBIP"), Integer.parseInt(config.getConfig("ForwardPort")));
}




/*public void init()// , Integer localport,String
					// remotehost, Integer
					// remoteport)
{
lpf = new ArrayList<LocalPortForwarder>();
}*/

}



