package com.yyy.system.service;

import com.yyy.system.api.model.LoginUser;
import com.yyy.system.vo.SysUserOnlineVO;

/**
 * 在线用户 服务层
 * 
* @author 羊扬杨
 */
public interface ISysUserOnlineService
{
    /**
     * 通过登录地址查询信息
     * 
     * @param ipaddr 登录地址
     * @param user 用户信息
     * @return 在线用户信息
     */
    public SysUserOnlineVO selectOnlineByIpaddr(String ipaddr, LoginUser user);

    /**
     * 通过用户名称查询信息
     * 
     * @param userName 用户名称
     * @param user 用户信息
     * @return 在线用户信息
     */
    public SysUserOnlineVO selectOnlineByUserName(String userName, LoginUser user);

    /**
     * 通过登录地址/用户名称查询信息
     * 
     * @param ipaddr 登录地址
     * @param userName 用户名称
     * @param user 用户信息
     * @return 在线用户信息
     */
    public SysUserOnlineVO selectOnlineByInfo(String ipaddr, String userName, LoginUser user);

    /**
     * 设置在线用户信息
     * 
     * @param user 用户信息
     * @return 在线用户
     */
    public SysUserOnlineVO loginUserToUserOnline(LoginUser user);
}
