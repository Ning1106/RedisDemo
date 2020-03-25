package com.example.learnredis.controller;

import com.example.learnredis.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class StudentController {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @deprecated  redis之String
     * @param student
     */
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

    /**
     * @deprecated redis之集合
     * @return
     */
    @GetMapping("/getList")
    public List<String> getList(){
        ListOperations<String,String> list = redisTemplate.opsForList();
        list.leftPush("list","A");
        list.rightPush("list","B");
        list.leftPush("list","C");
        List<String> strList = list.range("list",0,1);
        return strList;
    }

    /**
     * @deprecated  reids之set
     * @return
     */
    @GetMapping("/set")
    public Set<String> set(){
        SetOperations<String,String> setOpe = redisTemplate.opsForSet();
        setOpe.add("女","A");
        setOpe.add("女","B");
        Set<String> set = setOpe.members("女");
        return set;
    }

    /**
     * @deprecated  redis之有序set——zset
     * @return
     */
    @GetMapping("/zset")
    public Set<String> zset(){
        ZSetOperations<String,String> zset = redisTemplate.opsForZSet();
        zset.add("girl","A",2);
        zset.add("girl","B",3);
        zset.add("girl","C",1);
        Set<String> set = zset.range("girl", 0, 2);
        return set;
    }

    /**
     * @deprecated  redis之hash
     * @return
     */
    @GetMapping("/hash")
    public String hash(){
        HashOperations<String,String,String> hash = redisTemplate.opsForHash();
        hash.put("key","hashKey","A");
        String str = hash.get("key","hashKey");
        return str;
    }
}
