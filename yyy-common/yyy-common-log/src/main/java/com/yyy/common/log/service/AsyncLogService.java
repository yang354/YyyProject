package com.yyy.common.log.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.yyy.common.core.constant.SecurityConstants;
import com.yyy.system.api.RemoteLogService;
import com.yyy.system.api.vo.SysOperLogVO;

/**
 * 异步调用日志服务
 * 
* @author 羊扬杨
 */
@Service
public class AsyncLogService
{
    @Autowired
    private RemoteLogService remoteLogService;

    /**
     * 保存系统日志记录
     */
    @Async
    public void saveSysLog(SysOperLogVO SysOperLogVO)
    {
        remoteLogService.saveLog(SysOperLogVO, SecurityConstants.INNER);
    }
}
