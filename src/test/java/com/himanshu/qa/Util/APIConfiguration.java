/*Author: Himanshu Verma
 Project: SampleCodeAutomation
 */

package com.himanshu.qa.Util;

import com.himanshu.qa.Config.Config;

public class APIConfiguration {

	String path = System.getProperty("user.dir");

	public static String searchIP,serverIP,SSHIP;

	public APIConfiguration() {
		try {
			Config config = Config.getInstance();
			if (path.contains("mobileteam")) {
				searchIP = config.getConfig("SearchPrivateIP");
				// add here if need other component IP
				serverIP = config.getConfig("serverIPPrivate");
				SSHIP=config.getConfig("SSHIPPrivate");
			} else {
				searchIP = config.getConfig("SearchPublicIP");
				// add here if need other component IP
				serverIP = config.getConfig("serverIPPublic");
				SSHIP=config.getConfig("SSHIPPublic");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
