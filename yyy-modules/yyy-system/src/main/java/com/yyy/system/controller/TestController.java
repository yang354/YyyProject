package com.yyy.system.controller;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyy.common.core.domain.R;


import com.yyy.common.redis.utils.RedisLock;
import com.yyy.common.security.utils.SecurityUtils;
import com.yyy.system.api.domain.SysDept;
import com.yyy.system.mapper.SysDeptMapper;
import com.yyy.system.service.ISysDeptService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @Author yzz
 * @Date 2023/5/30 下午6:36
 */

@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${fileTemplate.profile}")
    private String profilePath;
    @Resource
    private RedisLock redisLock;
    @Resource
    private ISysDeptService sysDeptService;
    @Resource
    private SysDeptMapper sysDeptMapper;

    /**
     * 测试获取项目本地文件
     */
    @GetMapping("/getFileTemplate")
    public R getFileTemplate(){
        File file1 = new File(profilePath);

        System.out.println(file1.length()+"~~~~~~~~~~"+file1.getName());
        return R.ok("获取成功");
    }

    /**
     * 测试mybatis-plus
     */
    @GetMapping("/getDept")
    public R getDept(){

//        QueryWrapper<SysDept> sysDeptQueryWrapper = new QueryWrapper<>();
//        sysDeptQueryWrapper.eq("dept_id",104l);
//        SysDept SysDeptVO = sysDeptService.getOne(sysDeptQueryWrapper);
        System.out.println(SecurityUtils.getUsername()+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        SysDept sysDept = new SysDept();
        sysDept.setDeptName("菜市场部");
        sysDeptMapper.insert(sysDept);

        return R.ok("获取成功");
    }


    /**
     * 测试锁
     */
    @GetMapping("/getLock")
    public R getLock(){

        /*
        两个请求同时进来，第一个请求获取锁，往下执行
        而第二个请求堵在了redisLock.lock("aaa");这个一行，等待第一个请求执行完并释放锁，第二个请求才能往下走
         */
        try {
            redisLock.lock("aaa");
            System.out.println("aaaaa");
            try { TimeUnit.SECONDS.sleep(120); } catch (InterruptedException e) { e.printStackTrace(); }
        }finally {
            redisLock.unlock("aaa");
        }
        return R.ok("获取成功");
    }

    /**
     * 测试自动解锁
     */
    @GetMapping("/getunLock")
    public R getunLock(){
        /*

         */
        Boolean flag = redisLock.tryLock("aaa", 240); //设置了超时自动解锁，也要加上手动释放锁，保证规范写法
        if (flag){
            try {
                System.out.println("aaaaa");
                //try { TimeUnit.SECONDS.sleep(120); } catch (InterruptedException e) { e.printStackTrace(); }
            }finally {
                //因为测试超时自动解锁，所以没有打开手动解锁
                //redisLock.unlock("aaa");
            }
        } else {
            Assert.isTrue(false, "请勿重复操作!");
        }
        return R.ok("");
    }

}
