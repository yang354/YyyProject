package com.yyy.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.yyy.common.security.annotation.EnableCustomConfig;
import com.yyy.common.security.annotation.EnableRyFeignClients;
import com.yyy.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

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
    public static void main(String[] args)throws Exception {

        ConfigurableApplicationContext application = SpringApplication.run(YyyJobApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String property = env.getProperty("server.servlet.context-path");
        String path = property == null ? "" : property;
        System.out.println("(♥◠‿◠)ﾉﾞ  定时任务模块启动成功   ლ(´ڡ`ლ)ﾞ  ");
        System.out.println(
                "----------------------------------------------------------\n" +
                        "Application Yyy-Boot is running! Access URLs:\n" +
                        "Local: http://localhost:" + port + path + "/\n" +
                        "External: http://" + ip + ":" + port + path + "/\n" +
                        "Swagger: http://localhost:" + port + path + "/swagger-ui/index.html\n" +
                        "------------------------------------------------------------");
    }
}

