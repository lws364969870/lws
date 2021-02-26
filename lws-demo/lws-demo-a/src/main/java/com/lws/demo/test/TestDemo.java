package com.lws.demo.test;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;

import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

public class TestDemo implements Runnable {
    //共享资源
    static int i = 0;

    /**
     * synchronized 修饰实例方法
     */
    public synchronized void increase() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            increase();
        }
    }

    @Test
    public void matchSuffixPatternUsingFileExtensions2() {



        AntPathMatcher antPathMatcher = new AntPathMatcher();
        Map<String, String> result = antPathMatcher.extractUriTemplateVariables("/mgp/{systemCode}","/mgp/hello/hgh");

        System.out.println(result);

    }


    public static void main(String[] args) {
        PatternsRequestCondition c1 = new PatternsRequestCondition("/t1", "/t2");
        PatternsRequestCondition c2 = new PatternsRequestCondition("/m1", "/m2");


        System.out.println(c1.getPatterns());
    }

//    public static void main(String[] args) throws InterruptedException {
//        TestDemo test = new TestDemo();
//        Thread t1 = new Thread(test);
//        Thread t2 = new Thread(test);
//        t1.start();
//        t2.start();
//        t1.join();
//        t2.join();
//        System.out.println(i);
//    }
}