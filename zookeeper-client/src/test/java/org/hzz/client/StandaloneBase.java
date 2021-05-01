package org.hzz.client;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Before;

import java.util.concurrent.CountDownLatch;

public abstract class StandaloneBase {
    public static final String CONNECTION = "192.168.88.171:2181";
    public static final int TIME_OUT = 30*1000;
    private static final CountDownLatch countDownLatch = new CountDownLatch(1);
    private Watcher watcher = new Watcher() {
        @Override
        public void process(WatchedEvent event) {
            if(event.getType() == Event.EventType.None && event.getState() == Event.KeeperState.SyncConnected){
                System.out.println("连接zookeeper成功");
                countDownLatch.countDown();
            }
        }
    };

    private ZooKeeper zooKeeper = null;
    @Before
    public void init(){
        try{
            System.out.println("Start to connect to zookeeper");
            zooKeeper = new ZooKeeper(getConection(), getTimeOut(), watcher);
            System.out.println("connecting ... ");
            countDownLatch.await();
        }catch (Exception e){
            System.out.println("conect failed");
        }
    }

    protected String getConection(){
        return CONNECTION;
    }

    protected int getTimeOut(){
        return TIME_OUT;
    }

    protected ZooKeeper getZooKeeper(){
        return zooKeeper;
    }
}
