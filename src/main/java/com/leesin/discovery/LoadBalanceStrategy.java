package com.leesin.discovery;

import java.util.List;

/**
 * @description:
 * @author: Leesin.Dong
 * @date: Created in 2020/4/12 18:25
 * @version: ${VERSION}
 * @modified By:
 */
public interface LoadBalanceStrategy {
    String selectHost(List<String> repos);
}
