package org.hzz.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class CuratorBaseOperations extends CuratorStandalongBase{

    // 递归创建子节点
    @Test
    public void createNode() throws Exception {
        String pathWithParent = "/curator/subNode1";
        getCuratorFramework().create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(pathWithParent,"first".getBytes());
        System.out.println("create success");
    }
    //  protection模式，防止由于异常原因，创建幽灵节点
    @Test
    public void createNodeWithProtection() throws  Exception{
        String pathWithParent = "/curator/subNode2";
        getCuratorFramework().create()
                .creatingParentsIfNeeded()
                .withProtection()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(pathWithParent,"second2".getBytes());
        //  /curator/_c_c639d45f-3dc2-45cd-97a9-1e109f3bc7a0-subNode2
        System.out.println("create success with protection");
    }

    // 获取数据
    @Test
    public void testGetData() throws Exception{
        System.out.println("start...");
        //  获取不到
        try{
            byte[] data = getCuratorFramework().getData().forPath("/curator/subNode2");
            System.out.println("get data: "+new String(data));
        }catch (Exception e){
            System.out.println("error");
        }
        // 只有带上随机字符串才能获取到，但是这个字符串前缀是随机生成的
//        byte[] data = getCuratorFramework().getData().forPath("/curator/_c_8b787a60-7fdc-43da-b7cb-0242e1bfa447-subNode2");
    }

    //  设置数据
    @Test
    public void testSetData(){
        try {
            getCuratorFramework().setData().forPath("/curator/subNode1","third".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 删除节点
    @Test
    public void testDelete() throws Exception {
        getCuratorFramework().delete().guaranteed().deletingChildrenIfNeeded().forPath("/curator/delete-node");
        System.out.println("success");
    }

    //  获取所有子节点
    @Test
    public void testGetChildren() throws Exception{
        //  [subnode1,subnode2] 只是获取子节点，并不获取到孙子节点
        List<String> childrens = getCuratorFramework().getChildren().forPath("/curator");
        childrens.forEach(System.out::println);
    }


    //  线程池测试
    @Test
    public void testThreadPool() throws Exception{
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        byte[] bytes = getCuratorFramework().getData().inBackground((client, event) -> {
            //  Thread: pool-4-thread-1  data: hello world
            System.out.printf("Thread: %s  data: %s\n",Thread.currentThread().getName(),new String(event.getData()));
        },executorService).forPath("/curator/zk");
        System.out.println("get data: "+new String(bytes));
    }


}
