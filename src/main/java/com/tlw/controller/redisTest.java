package com.tlw.controller;

import com.google.gson.Gson;
import com.tlw.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@RestController
public class redisTest {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @GetMapping("/test")
    public void test(){
        Gson gson = new Gson();
        User user = new User("tlw","123456");
        redisTemplate.opsForValue().set("u1",gson.toJson(user),5, TimeUnit.MINUTES);
        //将json字符串转换成对象
        System.out.println(gson.fromJson(redisTemplate.opsForValue().get("u1"),User.class));
    }

    @GetMapping("/jedis")
    public String jedis(){
        Jedis jedis = new Jedis("192.168.192.128",6379);
        String value = jedis.ping();
        return value;
    }

    @GetMapping("demo1")
    public String demo1(){
        Jedis jedis = new Jedis("192.168.192.128",6379);
        /**
         * string
         */
//        jedis.set("name","mary");
//        jedis.mset("k1","v1","k2","v2");
//        jedis.get("name");

        /**
         * list
         */

//        jedis.lpush("l1","x","y","z");
//        jedis.lrange("l1",0,-1);

        /**
         * set
         */
//        jedis.sadd("s","v","b","n");
//        jedis.smembers("s");

        /**
         * hash
         */
        jedis.hset("user","username","jack");
        jedis.hset("user","password","123456");
        jedis.hget("user","name");
        Set<String> keys = jedis.keys("*");

        return keys.toString();
    }
}
