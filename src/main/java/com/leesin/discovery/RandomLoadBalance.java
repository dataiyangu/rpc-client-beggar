package com.leesin.discovery;

import java.util.List;
import java.util.Random;

/**
 * @description:
 * @author: Leesin.Dong
 * @date: Created in 2020/4/12 18:23
 * @version: ${VERSION}
 * @modified By:
 */
public class RandomLoadBalance extends AbstractLoadBalance{

    @Override
    protected String doSelect(List<String> repos) {
        int size = repos.size();
        Random random = new Random(); //从repos的集合内容随机获得一个地址
        return repos.get(random.nextInt(size));
    }
}
