package com.leesin.discovery;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Leesin.Dong
 * @date: Created in 2020/4/12 18:05
 * @version: ${VERSION}
 * @modified By:
 */
public class ServiceDiscoveryWithZk implements IServiceDiscovery {

    static CuratorFramework curatorFramework =null;

    List<String> serviceRepos=new ArrayList<>(); //服务地址的本地缓存

    {
        //初始化zookeeper的连接， 会话超时时间是5s，衰减重试
        curatorFramework = CuratorFrameworkFactory.builder().
                connectString(ZkConfig.CONNECTION_STR).sessionTimeoutMs(5000).
                retryPolicy(new ExponentialBackoffRetry(1000, 3)).
                namespace("registry")
                .build();
        curatorFramework.start();
    }


    @Override
    public String discovery(String serviceName) throws Exception {
        String path = "/" + serviceName;
        if (serviceRepos.isEmpty()) {
            serviceRepos = curatorFramework.getChildren().forPath(path);
            registruWatch(path);
        }

        LoadBalanceStrategy loadBalanceStrategy = new RandomLoadBalance();
        return loadBalanceStrategy.selectHost(serviceRepos);
    }

    public  void registruWatch(final String path) throws Exception {
        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework, path, true);
        PathChildrenCacheListener pathChildrenCacheListener = (curatorFramework1, pathChildrenCacheEvent)->{
            System.out.println("客户端收到变更的事件");
            serviceRepos = curatorFramework.getChildren().forPath(path);//更新本地的缓存地址
        };
        pathChildrenCache.getListenable().addListener(pathChildrenCacheListener);
        pathChildrenCache.start();
    }
}
