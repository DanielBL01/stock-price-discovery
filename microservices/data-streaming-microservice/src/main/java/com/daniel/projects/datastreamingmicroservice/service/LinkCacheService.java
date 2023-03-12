package com.daniel.projects.datastreamingmicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class LinkCacheService {

    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public LinkCacheService(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public void add(String link) {
        stringRedisTemplate.opsForValue().set(link, "");
    }

    public boolean exists(String link) {
        return stringRedisTemplate.hasKey(link);
    }
}
