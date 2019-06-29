package com.tiny.spot.mongo;

import java.util.concurrent.TimeUnit;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;

public class HashedWheelTimerDemo {
	public static void main(String[] args) throws Exception {
		HashedWheelTimer wheelTimer = new HashedWheelTimer(10, TimeUnit.MILLISECONDS);
		for (int i = 0; i < 1024 * 1024; i++) {
			final int j = i;
			wheelTimer.newTimeout(new TimerTask() {
				@Override
				public void run(Timeout timeout) throws Exception {
					System.out.println(j);
				}
			}, 3000, TimeUnit.MILLISECONDS);
		}
		Thread.sleep(Integer.MAX_VALUE);
	}
}
