package com.lws.demo.consumer.config;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.cluster.Router;
import org.apache.dubbo.rpc.cluster.RouterFactory;

@Activate
public class GrayRouterFactory implements RouterFactory {

    public static final String NAME = "gray";

    @Override
    public Router getRouter(URL url) {
        return new GrayRouter(url);
    }
}
