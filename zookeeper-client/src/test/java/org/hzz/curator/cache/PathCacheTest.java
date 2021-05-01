package org.hzz.curator.cache;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.hzz.curator.CuratorStandalongBase;
import org.junit.Test;

public class PathCacheTest extends CuratorStandalongBase {
    @Test
    public void test() throws Exception {
        String path = "/curator/path-cache";
        createdIfNeed(path);
        CuratorFramework client = getCuratorFramework();
        PathChildrenCache pathChildrenCache = new PathChildrenCache(client, path, true);

        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                System.out.println("event "+event);
            }
        });
        pathChildrenCache.start();
    }
}
/**
 event PathChildrenCacheEvent{type=CHILD_ADDED, data=ChildData{path='/curator/path-cache/subpath', stat=261,261,1619798162153,1619798162153,0,0,0,0,0,0,261
 , data=null}}
 event PathChildrenCacheEvent{type=CHILD_REMOVED, data=ChildData{path='/curator/path-cache/subpath', stat=261,261,1619798162153,1619798162153,0,0,0,0,0,0,261
 , data=null}}
 */