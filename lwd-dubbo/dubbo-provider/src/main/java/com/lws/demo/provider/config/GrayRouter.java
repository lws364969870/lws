package com.lws.demo.provider.config;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.cluster.router.condition.ConditionRouter;

public class GrayRouter extends ConditionRouter {

    public GrayRouter(URL url) {
        super(url);
    }

}
