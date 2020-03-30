package com.leesin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: Leesin.Dong
 * @date: Created in 2020/3/30 14:57
 * @version: ${VERSION}
 * @modified By:
 */
@Configuration
@ComponentScan("com.leesin")
public class SrpingConfig {
    @Bean
    public RpcProxyClient RpcProxyClient() {
        return new RpcProxyClient();
    }
}
