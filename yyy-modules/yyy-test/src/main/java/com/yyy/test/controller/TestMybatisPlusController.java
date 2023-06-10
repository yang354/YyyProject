package com.yyy.test.controller;

import com.yyy.common.core.constant.SecurityConstants;
import com.yyy.common.core.domain.R;
import com.yyy.common.redis.annotation.RateLimiter;
import com.yyy.system.api.RemoteUserService;
import com.yyy.system.api.vo.login.LoginUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author yzz
 * @Date 2023/6/10 上午12:52
 */

@Api(tags = "测试MybatisPlus功能")
@Slf4j
@RestController
@RequestMapping("mybatis")
public class TestMybatisPlusController {

    @Autowired
    private RemoteUserService remoteUserService;

        /**
     * 测试mybatis-plus、测试缓存
     */
    //@DS("master")  不写默认是master数据源
    @ApiOperation("测试mybatis-plus、测试缓存")
    //@Cacheable(value = "Dept", key = "'getDept'",sync = true)
    @RateLimiter
    @GetMapping("/testMybatisPlus")
    public R getDept(){
        System.out.println("如果第二次没有走到这里说明缓存被添加了，直接返回");
        R<LoginUserVO> userInfo = remoteUserService.getUserInfo("admin", SecurityConstants.INNER);


        return R.ok(userInfo,"获取成功");
    }
}
