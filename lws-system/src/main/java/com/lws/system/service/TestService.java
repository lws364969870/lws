package com.lws.system.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lws.system.entity.System;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TestService {

    @Autowired
    private ISystemService iSystemService;

    @Cacheable(value = "basic:system", key = "#systemCode")
    public System find(String systemCode) {
        return getSystemByDB(systemCode);
    }

    @Cacheable(value = "basic:system")
    public List<System> findAll() {
        return findAllByDB();
    }

//    @CachePut
//    public boolean update() {
//
//    }

//    @CacheEvict
//    public boolean delete() {
//
//    }

    public System getSystemByDB(String systemCode) {
        log.info("getSystemByDB");
        return iSystemService.getOne(new QueryWrapper<System>().eq("system_Code", systemCode));
    }

    public List<System> findAllByDB() {
        log.info("findAll");
        return iSystemService.list();
    }


}
