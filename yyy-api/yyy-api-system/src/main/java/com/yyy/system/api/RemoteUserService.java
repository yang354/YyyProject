package com.yyy.system.api;

import com.yyy.system.api.vo.SysUserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import com.yyy.common.core.constant.SecurityConstants;
import com.yyy.common.core.constant.ServiceNameConstants;
import com.yyy.common.core.domain.R;
import com.yyy.system.api.factory.RemoteUserFallbackFactory;
import com.yyy.system.api.model.LoginUser;

/**
 * 用户服务
 * 
* @author 羊扬杨
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService
{
    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @param source 请求来源
     * @return 结果
     */
    @GetMapping("/user/info/{username}")
    public R<LoginUser> getUserInfo(@PathVariable("username") String username, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 注册用户信息
     *
     * @param SysUserVO 用户信息
     * @param source 请求来源
     * @return 结果
     */
    @PostMapping("/user/register")
    public R<Boolean> registerUserInfo(@RequestBody SysUserVO SysUserVO, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
