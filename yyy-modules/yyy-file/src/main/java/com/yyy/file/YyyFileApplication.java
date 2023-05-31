package com.yyy.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import com.yyy.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 文件服务
 * 
* @author 羊扬杨
 */
@EnableCustomSwagger2
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class YyyFileApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(YyyFileApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  文件服务模块启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }
}
