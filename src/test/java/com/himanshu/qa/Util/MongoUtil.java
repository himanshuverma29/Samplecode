/*Author: Himanshu Verma
 Project: SampleCodeAutomation
 */
package com.himanshu.qa.Util;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.himanshu.qa.Config.Config;

class MongoUtil {

	static Config config;
	static MongoClient mongoClient;
	static DBCollection collection;
	static DB db;
	
	
	static{
		try {
			config	=	Config.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void createMongoConnection() throws Exception {

		mongoClient = new MongoClient(config.getConfig("MongoIP"), Integer.parseInt(config.getConfig("MongoPort")));
		db = mongoClient.getDB(config.getConfig("MongoDB"));
		System.out.println("MongoDB connection created successfully !!!");
	}

	static void closeConnection() {
		mongoClient.close();
		System.out.println("MongoDB connection closed successfully !!!");
	}

	
}
