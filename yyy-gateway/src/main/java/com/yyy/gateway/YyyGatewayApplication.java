package com.yyy.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

/**
 * 网关启动程序
 * 
* @author 羊扬杨
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class YyyGatewayApplication
{
    public static void main(String[] args)throws Exception {

        ConfigurableApplicationContext application = SpringApplication.run(YyyGatewayApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String property = env.getProperty("server.servlet.context-path");
        String path = property == null ? "" : property;
        System.out.println("(♥◠‿◠)ﾉﾞ  网关启动成功   ლ(´ڡ`ლ)ﾞ  ");
        System.out.println(
                "----------------------------------------------------------\n" +
                        "Application Yyy-Boot is running! Access URLs:\n" +
                        "Local: http://localhost:" + port + path + "/\n" +
                        "External: http://" + ip + ":" + port + path + "/\n" +
                        "Swagger: http://localhost:" + port + path + "/swagger-ui/index.html\n" +
                        "------------------------------------------------------------");
    }
}

