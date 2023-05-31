package com.yyy.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.yyy.common.security.annotation.EnableCustomConfig;
import com.yyy.common.security.annotation.EnableRyFeignClients;
import com.yyy.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 定时任务
 * 
* @author 羊扬杨
 */
@EnableCustomConfig
@EnableCustomSwagger2   
@EnableRyFeignClients
@SpringBootApplication
public class YyyJobApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(YyyJobApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  定时任务模块启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }
}
