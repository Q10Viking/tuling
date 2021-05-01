package leader;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.sql.Timestamp;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class LeaderDemo {
    public static final String CONNECT_STR = "192.168.88.171:2181,192.168.88.171:2182,192.168.88.171:2183,192.168.88.171:2184";
    public static final CountDownLatch countDownLatch = new CountDownLatch(1);
    public static void main(String[] args) throws InterruptedException {
        String appName = System.getProperty("server.port");
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(5 * 1000, 10);
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .retryPolicy(retryPolicy)
                .connectString(CONNECT_STR)
                .build();

        curatorFramework.start();

        LeaderSelectorListener listener = new LeaderSelectorListenerAdapter() {
            @Override
            public void takeLeadership(CuratorFramework client) throws Exception {
                System.out.printf("%s is leader\n",appName);
                TimeUnit.SECONDS.sleep(10);
            }
        };

        LeaderSelector leaderSelector = new LeaderSelector(curatorFramework, "/leader_path", listener);
        leaderSelector.autoRequeue();
        leaderSelector.start();
        System.out.printf("%s started",appName);
        countDownLatch.await();

    }
}
