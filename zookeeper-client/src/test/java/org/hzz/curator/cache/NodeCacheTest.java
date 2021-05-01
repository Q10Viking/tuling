package org.hzz.curator.cache;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.hzz.curator.CuratorStandalongBase;
import org.junit.Test;

public class NodeCacheTest extends CuratorStandalongBase {
    @Test
    public void testNodeCache() throws Exception {
        String path = "/curator/node-cache";
        createdIfNeed(path);
        CuratorFramework client = getCuratorFramework();
        NodeCache nodeCache = new NodeCache(client, path);
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println(path+": 数据发生变化");
                printData(path);
            }
        });

        nodeCache.start();
    }

    private void printData(String path) throws Exception{
        byte[] bytes = getCuratorFramework().getData().forPath(path);
        System.out.println("数据为"+new String(bytes));
    }
}
/**
 * /curator/node-cache: 数据发生变化
 * 数据为xxx
 * /curator/node-cache: 数据发生变化
 * 数据为yyy
 * /curator/node-cache: 数据发生变化
 * 数据为zz
 */
