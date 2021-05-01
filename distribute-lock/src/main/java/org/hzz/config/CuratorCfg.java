package org.hzz.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CuratorCfg {
    public static final String CONNECT_STR = "192.168.88.171:2181,192.168.88.171:2182,192.168.88.171:2183,192.168.88.171:2184";
    @Bean(initMethod = "start")
    public CuratorFramework curatorFramework(){

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 2);
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .retryPolicy(retryPolicy)
                .connectString(CONNECT_STR)
                .build();
        return curatorFramework;
    }
}
