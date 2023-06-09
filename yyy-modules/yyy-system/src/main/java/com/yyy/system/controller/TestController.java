package com.yyy.system.controller;

import cn.hutool.core.lang.Assert;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyy.common.core.domain.R;


import com.yyy.common.file.service.ISysFileService;
import com.yyy.common.redis.annotation.RateLimiter;
import com.yyy.common.redis.service.RedisService;
import com.yyy.common.redis.utils.RedisLock;
import com.yyy.common.security.utils.SecurityUtils;
import com.yyy.common.sms.service.SmsService;
import com.yyy.system.domain.SysDept;
import com.yyy.system.api.vo.SysFileVO;
import com.yyy.system.mapper.SysDeptMapper;
import com.yyy.system.service.AccountInfoService;
import com.yyy.system.service.ISysDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author yzz
 * @Date 2023/5/30 下午6:36
 */

@Api(tags = {"测试功能"})
@Slf4j
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
    @Autowired
    private SmsService smsService;


    /**
     * 测试获取项目本地文件
     */
    @ApiOperation("测试获取项目本地文件")
    @GetMapping("/testProjectLocalFile")
    public R getFileTemplate(){
        File file1 = new File(profilePath);

        System.out.println(file1.length()+"~~~~~~~~~~"+file1.getName());
        return R.ok("获取成功");
    }

    /**
     * 测试mybatis-plus、测试缓存
     */
    //@DS("master")  不写默认是master数据源
    @ApiOperation("测试mybatis-plus、测试缓存")
    @Cacheable(value = "Dept", key = "'getDept'",sync = true)
    //@RateLimiter
    @GetMapping("/testMybatisPlus")
    public R getDept(){
        System.out.println("如果第二次没有走到这里说明缓存被添加了，直接返回");

        QueryWrapper<SysDept> sysDeptQueryWrapper = new QueryWrapper<>();
        sysDeptQueryWrapper.eq("dept_id",104l);
        SysDept SysDeptVO = sysDeptService.getOne(sysDeptQueryWrapper);
        System.out.println(SecurityUtils.getUsername()+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//        SysDept sysDept = new SysDept();
//        sysDept.setDeptName("菜市场部");
//        sysDeptMapper.insert(sysDept);

        return R.ok(SysDeptVO,"获取成功");
    }


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




    @ApiOperation("测试获取手机验证码")
    @GetMapping("/sendVerificationCode")
    @ResponseBody
    public R sendVerificationCode(String phone) {
        try {

//            if (StringUtils.isBlank(phone)){
//                return R.fail("phone不能为空!");
//            }
            return R.ok(smsService.sendVerificationCode("18289339306"),"发送成功!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return R.fail("请求失败");
    }


    @Autowired
    private ISysFileService sysFileService;

    /**
     * 文件上传请求   @RequestPart使用了这个注解，swagger的ui上就出现了文件选择框
     */
    @ApiOperation("测试文件上传请求")
    @PostMapping("testUpload")
    public R<SysFileVO> upload(@RequestPart(value = "file") MultipartFile file)
    {
        try
        {
            // 上传并返回访问地址
            R<SysFileVO> sysFile = sysFileService.uploadFile(file);

            return sysFile;
        }
        catch (Exception e)
        {
            log.error("上传文件失败", e);
            return R.fail(e.getMessage());
        }
    }


    @Autowired
    AccountInfoService accountInfoService;

    /**
     * 测试多数据源
     */
    @ApiOperation("测试多数据源")
    @GetMapping("/testDS")
    public String transfer(Double amount){
        accountInfoService.updateAccountBalance("1",amount);
        return "bank1"+amount;
    }
}
