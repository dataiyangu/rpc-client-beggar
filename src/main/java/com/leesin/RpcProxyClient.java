package com.leesin;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.rmi.Remote;

/**
 * @description:
 * @author: Leesin.Dong
 * @date: Created in 2020/3/30 9:50
 * @version: ${VERSION}
 * @modified By:
 */
public class RpcProxyClient {

    public <T> T clientProxy(final Class<T> interfaces ,final String host,final int port) {
        T t= (T) Proxy.newProxyInstance(interfaces.getClassLoader(), new Class<?>[]{interfaces}, new RemoteInvocationHandler(host, port));
        return t;
    }
}
