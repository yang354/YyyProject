package com.yyy.auth.service;

import com.yyy.system.api.vo.SysLogininforVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.yyy.common.core.constant.Constants;
import com.yyy.common.core.constant.SecurityConstants;
import com.yyy.common.core.utils.ServletUtils;
import com.yyy.common.core.utils.StringUtils;
import com.yyy.common.core.utils.ip.IpUtils;
import com.yyy.system.api.RemoteLogService;


/**
 * 记录日志方法
 * 
* @author 羊扬杨
 */
@Component
public class SysRecordLogService
{
    @Autowired
    private RemoteLogService remoteLogService;

    /**
     * 记录登录信息
     * 
     * @param username 用户名
     * @param status 状态
     * @param message 消息内容
     * @return
     */
    public void recordLogininfor(String username, String status, String message)
    {
        SysLogininforVO logininfor = new SysLogininforVO();
        logininfor.setUserName(username);
        logininfor.setIpaddr(IpUtils.getIpAddr(ServletUtils.getRequest()));
        logininfor.setMsg(message);
        // 日志状态
        if (StringUtils.equalsAny(status, Constants.LOGIN_SUCCESS, Constants.LOGOUT, Constants.REGISTER))
        {
            logininfor.setStatus(Constants.LOGIN_SUCCESS_STATUS);
        }
        else if (Constants.LOGIN_FAIL.equals(status))
        {
            logininfor.setStatus(Constants.LOGIN_FAIL_STATUS);
        }
        remoteLogService.saveLogininfor(logininfor, SecurityConstants.INNER);
    }
}
