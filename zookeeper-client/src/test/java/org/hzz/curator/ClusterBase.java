package org.hzz.curator;

import org.hzz.curator.CuratorStandalongBase;

public class ClusterBase extends CuratorStandalongBase {
    public static final String CONNECT_STR = "192.168.88.171:2181,192.168.88.171:2182,192.168.88.171:2183,192.168.88.171:2184";

    public String getConnectionStr(){
        return CONNECT_STR;
    }
}
