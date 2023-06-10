package com.yyy.test.controller;


import com.yyy.common.core.domain.R;



import com.yyy.common.sms.service.SmsService;


import com.yyy.test.service.AccountInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

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
@RequestMapping("test")
public class TestController {


    @Autowired
    private SmsService smsService;


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
    private AccountInfoService accountInfoService;

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
