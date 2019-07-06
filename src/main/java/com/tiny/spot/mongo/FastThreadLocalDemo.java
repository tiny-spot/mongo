package com.tiny.spot.mongo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import io.netty.util.concurrent.DefaultThreadFactory;
import io.netty.util.concurrent.FastThreadLocal;

public class FastThreadLocalDemo {
	static FastThreadLocal<String> Context = new FastThreadLocal<>();

	public static void main(String[] args) {
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 5, 60, TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<Runnable>(10), new DefaultThreadFactory("fastThreadLocal"),
				new ThreadPoolExecutor.AbortPolicy());

		for (int i = 0; i < 50; i++) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			threadPoolExecutor.submit(new Runnable() {

				@Override
				public void run() {
					try {

						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						Context.set(Thread.currentThread().getName());
						System.out.println(Context.get());
					} finally {
						Context.remove();
					}
				}
			});
		}

		try {
			Thread.sleep(Integer.MAX_VALUE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
