package com.yyy.rabbit;


import com.yyy.common.security.annotation.EnableCustomConfig;
import com.yyy.common.security.annotation.EnableRyFeignClients;
import com.yyy.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
//@SpringBootApplication
public class YyyRabbitApplication {

    public static void main(String[] args) {
        SpringApplication.run(YyyRabbitApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  mq模块启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }

}
