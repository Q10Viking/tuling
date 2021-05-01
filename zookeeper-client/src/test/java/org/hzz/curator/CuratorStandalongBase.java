package org.hzz.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;

import java.util.Currency;
import java.util.concurrent.TimeUnit;

@Slf4j
public abstract class CuratorStandalongBase {
    public static final String CONNECTION_STR = "192.168.88.171:2181";
    public static final int SESSION_TIMEOUT = 60*1000;
    public static final int CONNECTION_TIMEOUT = 5000;


    private static CuratorFramework curatorFramework;

    @Before
    public void init() throws InterruptedException {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(5000, 10);
        curatorFramework = CuratorFrameworkFactory.builder()
                .retryPolicy(retryPolicy)
                .connectString(getConnectionStr())
                .sessionTimeoutMs(SESSION_TIMEOUT)
                .connectionTimeoutMs(CONNECTION_TIMEOUT)
                .build();

        curatorFramework.getConnectionStateListenable().addListener((client,newState)->{
            if(newState == ConnectionState.CONNECTED){
                System.out.println("connected success");
            }

        });
        System.out.println("connecting...");
        curatorFramework.start();
    }



    @After
    public void after(){
        try {
            System.out.println("over");
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected CuratorFramework getCuratorFramework(){
        return curatorFramework;
    }

    protected void createdIfNeed(String path) throws Exception {
        Stat stat = curatorFramework.checkExists().forPath(path);
        if(stat == null){
            curatorFramework.create().forPath(path);
            System.out.printf("%s created",path);
        }
    }

    protected String getConnectionStr(){
        return CONNECTION_STR;
    }
}
