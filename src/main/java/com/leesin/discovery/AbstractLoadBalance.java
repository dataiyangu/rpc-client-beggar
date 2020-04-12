package com.leesin.discovery;

import java.util.List;

/**
 * @description:
 * @author: Leesin.Dong
 * @date: Created in 2020/4/12 18:24
 * @version: ${VERSION}
 * @modified By:
 */
public abstract class AbstractLoadBalance implements LoadBalanceStrategy{
    @Override
    public String selectHost(List<String> repos) {
        //repos可能为空， 可能只有一个。
        if (repos == null&&repos.size()==0) {
            return null;
        }
        if (repos.size()==1) {
            return repos.get(0);
        }
        return doSelect(repos);
    }

    protected abstract String doSelect(List<String> repos);
}
