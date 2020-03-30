package com.leesin;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @description:
 * @author: Leesin.Dong
 * @date: Created in 2020/3/30 10:00
 * @version: ${VERSION}
 * @modified By:
 */
public class RemoteInvocationHandler implements InvocationHandler {
    String host;
    String port;
    public RemoteInvocationHandler(String host, int port) {
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParameters(args);
        rpcRequest.setVersion("v1.0");

        RpcNetTransport rpcNetTransport = new RpcNetTransport(host,port);
        Object result = rpcNetTransport.send(rpcRequest);
        return result;
    }
}
