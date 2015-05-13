/*Author: Himanshu Verma
 Project: SampleCodeAutomation
 */
package com.himanshu.qa.Util;

import java.util.ArrayList;

import com.himanshu.qa.Util.MongoUtil;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class MongoQuery {

	
	public double getValue(String collection, String key) throws Exception
	{
		double result	=	0;;
		DBObject db_object = findOne(collection);
	    result	=	 (Double) db_object.get(key);
		return result;
	}
	
	
	public static ArrayList<DBObject> find(String collection_name)
			throws Exception {

		ArrayList<DBObject> results = new ArrayList<DBObject>();
		MongoUtil.collection = MongoUtil.db.getCollection(collection_name);
		DBCursor cursor = MongoUtil.collection.find();
		try {
			while (cursor.hasNext()) {
				DBObject obj = cursor.next();
				results.add(obj);
			}

		} finally {
			cursor.close();
		}
		return results;
	}

	public static ArrayList<DBObject> find(String collection_name,
			BasicDBObject query) throws Exception {

		ArrayList<DBObject> results = new ArrayList<DBObject>();
		MongoUtil.collection = MongoUtil.db.getCollection(collection_name);
		DBCursor cursor = MongoUtil.collection.find(query);
		try {
			while (cursor.hasNext()) {
				DBObject obj = cursor.next();
				results.add(obj);
			}

		} finally {
			cursor.close();
		}
		return results;
	}

	public static ArrayList<DBObject> find(String collection_name,
			BasicDBObject query, BasicDBObject projection) throws Exception {

		ArrayList<DBObject> results = new ArrayList<DBObject>();
		MongoUtil.collection = MongoUtil.db.getCollection(collection_name);
		DBCursor cursor = MongoUtil.collection.find(query, projection);
		try {
			while (cursor.hasNext()) {
				DBObject obj = cursor.next();
				results.add(obj);
			}

		} finally {
			cursor.close();
		}
		return results;
	}

	public static DBObject findOne(String collection_name) throws Exception {

		DBObject result;
		MongoUtil.collection = MongoUtil.db.getCollection(collection_name);
		result = MongoUtil.collection.findOne();
		return result;
	}

	public static DBObject findOne(String collection_name, BasicDBObject query)
			throws Exception {

		DBObject result;

		MongoUtil.collection = MongoUtil.db.getCollection(collection_name);
		result = MongoUtil.collection.findOne(query);

		return result;
	}

	public static DBObject findOne(String collection_name, BasicDBObject query,
			BasicDBObject projection) throws Exception {

		DBObject result;
		MongoUtil.collection = MongoUtil.db.getCollection(collection_name);
		result = MongoUtil.collection.findOne(query, projection);

		return result;
	}

	public static void insert(String collection_name, BasicDBObject query)
			throws Exception {

		MongoUtil.collection = MongoUtil.db.getCollection(collection_name);
		MongoUtil.collection.insert(query);

	}
}
