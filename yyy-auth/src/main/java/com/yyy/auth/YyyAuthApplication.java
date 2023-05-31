package com.yyy.auth;


import com.yyy.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import com.yyy.common.security.annotation.EnableRyFeignClients;

/**
 * 认证授权中心
 * 
* @author 羊扬杨
 */
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class YyyAuthApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(YyyAuthApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  认证授权中心启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }
}
