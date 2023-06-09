package com.yyy.test.controller;

import cn.hutool.core.lang.Assert;
import com.yyy.common.core.domain.R;
import com.yyy.common.redis.utils.RedisLock;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author yzz
 * @Date 2023/6/10 上午12:12
 */

@Api(tags = {"测试分布式锁功能"})
@Slf4j
@RestController
@RequestMapping("/lcok")
public class TestLockController {

    @Resource
    private RedisLock redisLock;

    /**
     * 测试分布式锁
     */
    @ApiOperation("测试分布式锁")
    @GetMapping("/testLock")
    public R getLock(){
        /*
        如何测试：如下
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
    @ApiOperation("测试自动解锁")
    @GetMapping("/testUnLock")
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
