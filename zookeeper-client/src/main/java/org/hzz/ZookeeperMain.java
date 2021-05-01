package org.hzz;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.ObjectMapper;


public class ZookeeperMain {
    public static final String CONNECTION = "192.168.88.171:2181";
    public static final int TIME_OUT = 30*1000;
    private static final CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {

        //  zookeeper内部时一个异步线程进行发送和接收数据的
        ZooKeeper zooKeeper = new ZooKeeper(CONNECTION, TIME_OUT, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if(event.getType()== Event.EventType.None
                    && event.getState() == Event.KeeperState.SyncConnected){

                    System.out.println("连接成功");
                    countDownLatch.countDown();
                }
            }
        });

        countDownLatch.await();
        MyConfig myConfig = new MyConfig();
        myConfig.setName("hzz");
        myConfig.setKey("zookeeper-test");

        ObjectMapper objectMapper = new ObjectMapper();
        byte[] bytes = objectMapper.writeValueAsBytes(myConfig);
        //  创建节点，设置数据与权限
        zooKeeper.create("/test-node",bytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("创建数据成功");

        Watcher watcher = new Watcher() {
            @SneakyThrows
            @Override
            public void process(WatchedEvent event) {
                if(event.getType() == Event.EventType.NodeDataChanged
                    && event.getPath()!=null && "/test-node".equals(event.getPath())){
                    System.out.println("数据发生变化");
                    //  重新获取并监听
                    byte[] data = zooKeeper.getData("/test-node", this, null);
                    MyConfig myConfig1 = objectMapper.readValue(new String(data), MyConfig.class);
                    System.out.println("读取发生变化的数据： "+myConfig1);
                }

            }
        };

        //  获取数据
        byte[] data = zooKeeper.getData("/test-node", watcher, null);
        MyConfig myConfig1 = objectMapper.readValue(new String(data), MyConfig.class);
        System.out.println("读取数据： "+myConfig1);


        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        System.out.println("结束");
    }
}
