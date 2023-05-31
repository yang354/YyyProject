package com.yyy.system.controller;

import com.yyy.common.core.domain.R;
import com.yyy.common.core.web.page.TableDataInfo;
import com.yyy.common.security.annotation.RequiresPermissions;
import com.yyy.common.swagger.config.SwaggerProperties;
import com.yyy.system.api.domain.SysUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author yzz
 * @Date 2023/5/30 下午6:36
 */

@RestController
@RequestMapping("/test")
public class TestController {
    @Resource
    private SwaggerProperties swaggerProperties;

    /**
     * 获取用户列表
     */

    @GetMapping("/getSwagger")
    public R list()
    {

        System.out.println(swaggerProperties.getBasePackage()+"~~~~~~~~~~~~~~~~`");
        return R.ok();
    }
}
