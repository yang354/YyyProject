package com.yyy.modules.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

/**
 * 监控中心
 * 
* @author 羊扬杨
 */
@EnableAdminServer
@SpringBootApplication
public class YyyMonitorApplication
{
    public static void main(String[] args)throws Exception {

        ConfigurableApplicationContext application = SpringApplication.run(YyyMonitorApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String property = env.getProperty("server.servlet.context-path");
        String path = property == null ? "" : property;
        System.out.println("(♥◠‿◠)ﾉﾞ  监控中心启动成功   ლ(´ڡ`ლ)ﾞ  ");
        System.out.println(
                "----------------------------------------------------------\n" +
                        "Application Yyy-Boot is running! Access URLs:\n" +
                        "Local: http://localhost:" + port + path + "/\n" +
                        "External: http://" + ip + ":" + port + path + "/\n" +
                        "Swagger: http://localhost:" + port + path + "/swagger-ui/index.html\n" +
                        "------------------------------------------------------------");
    }
}
