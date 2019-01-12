package com.tiny.spot.dubbo.provider;

import org.springframework.stereotype.Service;

@Service("demoService")
public class DemoServiceImpl implements DemoService {

	@Override
	public String sayHai(String name) {
		return "hello" + name;
	}

}
