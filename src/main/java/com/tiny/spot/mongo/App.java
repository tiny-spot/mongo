package com.tiny.spot.mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class App {
	public static void main(String[] args) {
		try {
			MongoClientOptions.Builder options = new MongoClientOptions.Builder();
			options.cursorFinalizerEnabled(true);
			// options.autoConnectRetry(true);// 自动重连true
			// options.maxAutoConnectRetryTime(10); // the maximum auto connect retry time
			options.connectionsPerHost(300);// 连接池设置为300个连接,默认为100
			options.connectTimeout(30000);// 连接超时，推荐>3000毫秒
			options.maxWaitTime(5000); //
			options.socketTimeout(0);// 套接字超时时间，0无限制
			options.threadsAllowedToBlockForConnectionMultiplier(5000);// 线程队列数，如果连接线程排满了队列就会抛出“Out of semaphores to get
			options.writeConcern(WriteConcern.SAFE);//
			options.build();
			// 连接到 mongodb 服务
			MongoClient mongoClient = new MongoClient("localhost", 27017);
			// 连接到数据库
			MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");
			// mongoDatabase.createCollection("test");
			MongoCollection<Document> collection = mongoDatabase.getCollection("test");
			System.out.println("集合 test 选择成功");
			// 插入文档
			/**
			 * 1. 创建文档 org.bson.Document 参数为key-value的格式 2. 创建文档集合List<Document> 3.
			 * 将文档集合插入数据库集合中 mongoCollection.insertMany(List<Document>) 插入单个文档可以用
			 * mongoCollection.insertOne(Document)
			 */
			Document document = new Document("title", "MongoDB").append("description", "database").append("likes", 100)
					.append("by", "Fly");
			collection.insertOne(document);
			System.out.println(document.get("_id"));
			List<Document> documents = new ArrayList<Document>();
			documents.add(document);
			// collection.insertMany(documents);
			System.out.println("文档插入成功");
			FindIterable<Document> tiFindIterable = collection.find(Filters.eq("title", "MongoDB"));
			MongoCursor<Document> iCursor = tiFindIterable.iterator();
			while (iCursor.hasNext()) {
				Document doc = iCursor.next();
				System.out.println(doc.get("_id"));
			}
			System.out.println("Connect to database successfully");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
}
