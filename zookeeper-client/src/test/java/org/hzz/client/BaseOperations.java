package org.hzz.client;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

@Slf4j
public class BaseOperations extends StandaloneBase {
    public static final String NODE_NAME = "/first-node";

    //  创建临时节点
    @Test
    public void testCreate() throws KeeperException, InterruptedException {
        ZooKeeper zooKeeper = getZooKeeper();
        zooKeeper.create(NODE_NAME, "first".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("创建成功");
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }

    //  获取数据
    @Test
    public void getData(){
        Watcher watcher = new Watcher() {
            @SneakyThrows
            @Override
            public void process(WatchedEvent event) {
                if(event.getType()== Event.EventType.NodeDataChanged
                    && NODE_NAME.equals(event.getPath())){
                    System.out.println("data changed");
                    //  循环监听
                    byte[] data = getZooKeeper().getData(NODE_NAME, this, null);
                    System.out.println("data change to "+new String(data));
                }
            }
        };

        try{
            byte[] data = getZooKeeper().getData(NODE_NAME, watcher, null);
            System.out.println("Get Data: "+new String(data));
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //  设置数据
    @Test
    public void setData() throws Exception {
        //  stat的使用
        Stat stat = new Stat();
        getZooKeeper().getData(NODE_NAME,null,stat);
        int version = stat.getVersion();
        getZooKeeper().setData(NODE_NAME,"second".getBytes(),version);
        System.out.println("set data success");
    }


    @Test
    public void testDelete() throws  Exception{
        // -1 代表匹配所有版本，直接删除
        // 任意大于 -1 的代表可以指定数据版本删除
        getZooKeeper().delete("/test",-1);
    }

    @Test
    public void testAsyncGetData() throws Exception{
        //  DataCallback
        getZooKeeper().getData("/test",null,( rc,  path,  ctx,data, stat) ->{
            Thread thread = Thread.currentThread();
            System.out.println("异步请求获取");
            System.out.printf("Thread=%s,rc=%s, path=%s,ctx=%s,data=%s,stat=%s\n",thread.getName(),rc,  path,  ctx,new String(data), stat);
        },"ctx-test");

        System.out.println("执行其他任务");
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
    /**
     * 执行其他任务
     * 异步请求获取
     * Thread=main-EventThread,rc=0, path=/test,ctx=ctx-test,data=xxx,stat=75,76,1619782943876,1619782947802,1,0,0,0,3,0,75
     */
}
