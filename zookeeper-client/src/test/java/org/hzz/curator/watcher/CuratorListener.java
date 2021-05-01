package org.hzz.curator.watcher;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.hzz.curator.CuratorStandalongBase;
import org.junit.Test;

public class CuratorListener extends CuratorStandalongBase {
    @Test
    public void testListener() {
        CuratorFramework curatorFramework = getCuratorFramework();
        curatorFramework.getCuratorListenable().addListener((client,event)->{
            System.out.printf("type: %s %s\n",event.getType().name(),event);
        });
        String path = "/curator/listener";
        try {
            curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).inBackground().forPath(path);
            curatorFramework.setData().inBackground().forPath(path,"listener-test".getBytes());
            curatorFramework.getData().inBackground().forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/**
 * type: WATCHED CuratorEventImpl{type=WATCHED, resultCode=3, path='null', name='null', children=null, context=null, stat=null, data=null, watchedEvent=WatchedEvent state:SyncConnected type:None path:null, aclList=null, opResults=null}
 * over
 * type: CREATE CuratorEventImpl{type=CREATE, resultCode=-110, path='/curator/listener', name='null', children=null, context=null, stat=null, data=null, watchedEvent=null, aclList=null, opResults=null}
 * type: SET_DATA CuratorEventImpl{type=SET_DATA, resultCode=0, path='/curator/listener', name='null', children=null, context=null, stat=233,240,1619796406991,1619796447228,3,0,0,72057612141920333,13,0,233
 * , data=null, watchedEvent=null, aclList=null, opResults=null}
 * type: GET_DATA CuratorEventImpl{type=GET_DATA, resultCode=0, path='/curator/listener', name='null', children=null, context=null, stat=233,240,1619796406991,1619796447228,3,0,0,72057612141920333,13,0,233
 * , data=[108, 105, 115, 116, 101, 110, 101, 114, 45, 116, 101, 115, 116], watchedEvent=null, aclList=null, opResults=null}
 */
