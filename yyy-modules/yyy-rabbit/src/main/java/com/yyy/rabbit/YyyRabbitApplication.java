package com.yyy.rabbit;


import com.yyy.common.security.annotation.EnableCustomConfig;
import com.yyy.common.security.annotation.EnableRyFeignClients;
import com.yyy.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
//@SpringBootApplication
public class YyyRabbitApplication {

    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext application = SpringApplication.run(YyyRabbitApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String property = env.getProperty("server.servlet.context-path");
        String path = property == null ? "" :  property;
        System.out.println("(♥◠‿◠)ﾉﾞ  mq模块启动成功   ლ(´ڡ`ლ)ﾞ  ");
        System.out.println(
                "----------------------------------------------------------\n" +
                "Application Yyy-Boot is running! Access URLs:\n" +
                "Local: http://localhost:" + port + path + "/\n" +
                "External: http://" + ip + ":" + port + path + "/\n" +
                "Swagger: http://localhost:" + port + path + "/swagger-ui/index.html\n" +
                "------------------------------------------------------------");
    }

}
