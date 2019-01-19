package com.tiny.spot.dubbo.app;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tiny.spot.dubbo.provider.DemoService;

public class Consumer {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "dubbo-consumer.xml" });
		context.start();
		DemoService demoService = (DemoService) context.getBean("demoService"); //
		String hello = demoService.sayHai("hi"); // ִ
		System.out.println(hello); //
	}

}
