package com.example.learnredis.controller;

import com.example.learnredis.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/save")
    public void save(@RequestBody Student student){
        redisTemplate.opsForValue().set("student",student);
    }

    @GetMapping("/get/{key}")
    public Student get(@PathVariable String key){
        return (Student)redisTemplate.opsForValue().get(key);
    }

    @GetMapping("/del/{key}")
    public boolean del(@PathVariable String key){
        redisTemplate.delete(key);
        return redisTemplate.hasKey(key);
    }
}
