package org.hzz.client;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public abstract class ClusterBase {
    public static final String CONNECTION = "192.168.88.171:2181,192.168.88.171:2182,192.168.88.171:2183,192.168.88.171:2184";
    public static final int SESSION_TIMEOUT = 30 * 1000;
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    private ZooKeeper zooKeeper;

    @Before
    public void start(){
        try {
            zooKeeper = new ZooKeeper(CONNECTION, SESSION_TIMEOUT, e -> {
                if(e.getType() == Watcher.Event.EventType.None){
                    System.out.println("连接建立");
                    countDownLatch.countDown();
                }
            });
            System.out.println("连接中...");
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void after() throws InterruptedException {
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }

    protected ZooKeeper getZooKeeper(){
        return  zooKeeper;
    }
}
