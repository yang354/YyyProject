package com.yyy.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 网关启动程序
 * 
* @author 羊扬杨
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class YyyGatewayApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(YyyGatewayApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  若依网关启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }
}
