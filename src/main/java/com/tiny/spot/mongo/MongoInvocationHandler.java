package com.tiny.spot.mongo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.mongodb.client.MongoCollection;

public class MongoInvocationHandler implements InvocationHandler {

	private MongoCollection<?> mongoCollection;

	public MongoInvocationHandler(MongoCollection<?> mongoCollection) {
		this.mongoCollection = mongoCollection;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		long start = System.currentTimeMillis();
		try {
			return method.invoke(mongoCollection, args);
		} finally {
			System.out.println(method.getName() + " cost:" + (System.currentTimeMillis() - start));
		}
	}

}
