package com.lws.gateway.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GatewayRouteDefinition {

    //路由的Id
    private String id;
    //路由断言集合配置
    private List<GatewayPredicateDefinition> predicates = new ArrayList<>();
    //路由过滤器集合配置
    private List<GatewayFilterDefinition> filters = new ArrayList<>();
    //路由规则转发的目标uri
    private String uri;
    //路由执行的顺序
    private Integer order = 0;
    //此处省略get和set方法


    public static void main(String[] args) {
        String a = "abc:defg";
        String b = "abc:";

        System.out.println(a.startsWith(b));
        System.out.println(b.startsWith(a));

    }
}
