package org.hzz.curator;

import org.apache.curator.framework.CuratorFramework;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class ClusterOpertions extends ClusterBase{

    @Test
    public void test() throws InterruptedException {
        CuratorFramework curatorFramework = getCuratorFramework();
        while(true){
            try {
                byte[] bytes  = curatorFramework.getData().forPath("/cluster-test");
                System.out.println("get data: "+new String(bytes));
            } catch (Exception e) {
                e.printStackTrace();
            }
            TimeUnit.SECONDS.sleep(5);
        }
    }
}
