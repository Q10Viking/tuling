package org.hzz.client;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class ClustorOperations extends ClusterBase{
    @Test
    public void testCluster() throws InterruptedException {
        while (true){
            try{
                byte[] data = getZooKeeper().getData("/cluster-test", null, null);
                System.out.println("get data: "+ new String(data));
                TimeUnit.SECONDS.sleep(5);
            }catch (Exception e){
                System.out.println("连接失败");
                //  通过catch手动处理重新连接
                while(true){
                    System.out.println("重新建立连接中... ...");
                    if(getZooKeeper().getState().isConnected()){
                        break;
                    }
                    TimeUnit.SECONDS.sleep(3);
                }
            }
        }

    }
}
