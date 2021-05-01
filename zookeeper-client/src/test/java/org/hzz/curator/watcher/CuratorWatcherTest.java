package org.hzz.curator.watcher;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.hzz.curator.CuratorStandalongBase;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class CuratorWatcherTest extends CuratorStandalongBase {

    // watcher机制
    @Test
    public void testWatcher() throws Exception {
        String path = "/curator/test";
        createdIfNeed(path);
        //  只是watch一次
        byte[] bytes = getCuratorFramework().getData().usingWatcher((Watcher) event -> {
            System.out.println("....");
            if(event.getType()== Watcher.Event.EventType.NodeDataChanged){
                System.out.println("数据发生了变化");
            }
        }).forPath(path);

//        System.out.println(" original data: "+new String(bytes));
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }

}
