package com.leesin.discovery;

/**
 * @description:
 * @author: Leesin.Dong
 * @date: Created in 2020/4/12 18:06
 * @version: ${VERSION}
 * @modified By:
 */
public interface IServiceDiscovery {

    //根据服务名称返回服务地址
    String discovery(String serviceName) throws Exception;
}
