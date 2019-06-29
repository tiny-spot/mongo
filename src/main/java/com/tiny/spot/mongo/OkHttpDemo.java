package com.tiny.spot.mongo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.squareup.okhttp.ConnectionPool;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class OkHttpDemo {

	static OkHttpClient client = new OkHttpClient();

	static Integer Counter = Runtime.getRuntime().availableProcessors() * 2;

	static {
		client.setConnectTimeout(1000, TimeUnit.MILLISECONDS);
		client.setReadTimeout(1000, TimeUnit.MILLISECONDS);
		client.setWriteTimeout(1000, TimeUnit.MILLISECONDS);

		ConnectionPool connectionPool = new ConnectionPool(1000, 30 * 1000);
		client.setConnectionPool(connectionPool);

		// 设置并发数
		client.getDispatcher().setMaxRequests(Counter * 100);
		client.getDispatcher().setMaxRequestsPerHost(Counter);
	}

	public static void main(String[] args) throws Exception {
		ExecutorService executorService = Executors.newFixedThreadPool(Counter);
		final CountDownLatch countDownLatch = new CountDownLatch(1);
		for (int i = 0; i < Counter; i++) {
			executorService.submit(new Runnable() {
				@Override
				public void run() {
					try {
						countDownLatch.await();
					} catch (InterruptedException e) {
					}
					while (true) {
						try {
							Request request = new Request.Builder().url("https://www.163.com/").build();
							long start = System.currentTimeMillis();
							Response response = client.newCall(request).execute();
							System.out.println(response + ", rt:" + (System.currentTimeMillis() - start));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			});
		}
		countDownLatch.countDown();
		Thread.sleep(Integer.MAX_VALUE);
	}

}
