package com.leesin;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        RpcProxyClient rpcProxyClient = new RpcProxyClient();
        // IHelloService helloService = rpcProxyClient.clientProxy(IHelloService.class,"localhost", 8080);
        // String shuai_bi = helloService.sayHello("shuai bi");
        IpaymentService helloService = (IpaymentService) rpcProxyClient.clientProxy(IpaymentService.class,"localhost", 8080);
        String shuai_bi = helloService.dopay();
        System.out.println(shuai_bi);
    }
}
