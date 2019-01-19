package com.tiny.spot.dubbo.provider;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.RpcResult;

public class TopFilter implements Filter {

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		String methodName = invocation.getMethodName();
		System.out.println(methodName);
		Result result = invoker.invoke(invocation);
		if (result.hasException()) {
			return new RpcResult();
		}
		return result;
	}

}
