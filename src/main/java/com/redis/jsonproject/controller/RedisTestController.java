package com.redis.jsonproject.controller;


import com.redis.jsonproject.model.APIResponse;
import com.redis.jsonproject.service.RedisTestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping(value = "/testredis")
public class RedisTestController {
    public static final String UTF8_BOM = "\uFEFF";

    @Autowired
    public RedisTestService redisTestService;
    @PostMapping(value = "/setdata")
    public boolean setDataToRedis(@RequestParam("file") MultipartFile file,
                                  @RequestParam("path")String path,
                                  @RequestParam("key")String key){

        try{
            String content = removeUTF8BOM(new String(file.getBytes()));
            return redisTestService.setRedisData(path,key,content);
        } catch (IOException e) {
            return false;
        }
    }
    @GetMapping(value = "/getdata")
    public Object geDataFromRedis(@RequestBody APIResponse request){
        return redisTestService.getRedisData(request.getPath(),Object.class,request.getKey());
    }
    @PutMapping(value = "/putdata")
    public Object updateDataFromRedis(@RequestBody APIResponse request){
        return redisTestService.setRedisData(request.getPath(),request.getKey(), request.getValue());
    }
    @PostMapping(value = "/operate")
    public Object performOpsOnRedis(@RequestBody APIResponse request){
        return redisTestService.performOps(request);
    }
    private static String removeUTF8BOM(String s) {
        if (s.startsWith(UTF8_BOM)) {
            s = s.substring(1);
        }
        return s;
    }

}
