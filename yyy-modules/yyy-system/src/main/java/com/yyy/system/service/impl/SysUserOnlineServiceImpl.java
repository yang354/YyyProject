package com.yyy.system.service.impl;

import org.springframework.stereotype.Service;
import com.yyy.common.core.utils.StringUtils;
import com.yyy.system.api.vo.login.LoginUserVO;
import com.yyy.system.vo.SysUserOnlineVO;
import com.yyy.system.service.ISysUserOnlineService;

/**
 * 在线用户 服务层处理
 * 
* @author 羊扬杨
 */
@Service
public class SysUserOnlineServiceImpl implements ISysUserOnlineService
{
    /**
     * 通过登录地址查询信息
     * 
     * @param ipaddr 登录地址
     * @param user 用户信息
     * @return 在线用户信息
     */
    @Override
    public SysUserOnlineVO selectOnlineByIpaddr(String ipaddr, LoginUserVO user)
    {
        if (StringUtils.equals(ipaddr, user.getIpaddr()))
        {
            return loginUserToUserOnline(user);
        }
        return null;
    }

    /**
     * 通过用户名称查询信息
     * 
     * @param userName 用户名称
     * @param user 用户信息
     * @return 在线用户信息
     */
    @Override
    public SysUserOnlineVO selectOnlineByUserName(String userName, LoginUserVO user)
    {
        if (StringUtils.equals(userName, user.getUsername()))
        {
            return loginUserToUserOnline(user);
        }
        return null;
    }

    /**
     * 通过登录地址/用户名称查询信息
     * 
     * @param ipaddr 登录地址
     * @param userName 用户名称
     * @param user 用户信息
     * @return 在线用户信息
     */
    @Override
    public SysUserOnlineVO selectOnlineByInfo(String ipaddr, String userName, LoginUserVO user)
    {
        if (StringUtils.equals(ipaddr, user.getIpaddr()) && StringUtils.equals(userName, user.getUsername()))
        {
            return loginUserToUserOnline(user);
        }
        return null;
    }

    /**
     * 设置在线用户信息
     * 
     * @param user 用户信息
     * @return 在线用户
     */
    @Override
    public SysUserOnlineVO loginUserToUserOnline(LoginUserVO user)
    {
        if (StringUtils.isNull(user))
        {
            return null;
        }
        SysUserOnlineVO SysUserOnlineVO = new SysUserOnlineVO();
        SysUserOnlineVO.setTokenId(user.getToken());
        SysUserOnlineVO.setUserName(user.getUsername());
        SysUserOnlineVO.setIpaddr(user.getIpaddr());
        SysUserOnlineVO.setLoginTime(user.getLoginTime());
        return SysUserOnlineVO;
    }
}
