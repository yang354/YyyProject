package com.yyy.system.service;

import java.util.Set;


import com.yyy.system.api.vo.SysUserVO;

/**
 * 权限信息 服务层
 * 
* @author 羊扬杨
 */
public interface ISysPermissionService
{
    /**
     * 获取角色数据权限
     * 
     * @param userId 用户Id
     * @return 角色权限信息
     */
    public Set<String> getRolePermission(SysUserVO user);

    /**
     * 获取菜单数据权限
     * 
     * @param userId 用户Id
     * @return 菜单权限信息
     */
    public Set<String> getMenuPermission(SysUserVO user);
}
