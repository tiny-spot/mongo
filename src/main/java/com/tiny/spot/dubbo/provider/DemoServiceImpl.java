package com.tiny.spot.dubbo.provider;

import org.springframework.stereotype.Service;

@Service("demoService")
public class DemoServiceImpl implements DemoService {

	@Override
	public String sayHai(String name) {
		if ("hi".equals(name)) {
			throw new RuntimeException("Error");
		}
		return "hello" + name;
	}

}
