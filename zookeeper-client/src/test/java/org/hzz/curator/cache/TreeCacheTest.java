package org.hzz.curator.cache;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.hzz.curator.CuratorStandalongBase;
import org.junit.Test;

import java.util.Map;

public class TreeCacheTest extends CuratorStandalongBase {
    public static final String TREE_CACHE="/curator/tree-path";

    @Test
    public void testTreeCache() throws Exception {
        CuratorFramework curatorFramework = getCuratorFramework();
        createdIfNeed(TREE_CACHE);
        TreeCache treeCache = new TreeCache(curatorFramework, TREE_CACHE);
        treeCache.getListenable().addListener(new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                System.out.println(" tree cache: {}"+event);
                Map<String, ChildData> currentChildren = treeCache.getCurrentChildren(TREE_CACHE);
                System.out.println("currentChildren: "+currentChildren);
            }
        });
        treeCache.start();
    }
}

/**
 tree cache: {}TreeCacheEvent{type=NODE_UPDATED, data=ChildData{path='/curator/tree-path/subtree', stat=275,279,1619798670780,1619798711126,4,0,0,0,2,0,275
 , data=[121, 121]}}
 currentChildren: {subtree=ChildData{path='/curator/tree-path/subtree', stat=275,279,1619798670780,1619798711126,4,0,0,0,2,0,275
 , data=[121, 121]}}
 */