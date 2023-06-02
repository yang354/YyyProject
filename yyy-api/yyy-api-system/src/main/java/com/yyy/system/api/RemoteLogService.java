package com.yyy.system.api;

import com.yyy.system.api.vo.SysLogininforVO;
import com.yyy.system.api.vo.SysOperLogVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import com.yyy.common.core.constant.SecurityConstants;
import com.yyy.common.core.constant.ServiceNameConstants;
import com.yyy.common.core.domain.R;


import com.yyy.system.api.factory.RemoteLogFallbackFactory;

/**
 * 日志服务
 * 
* @author 羊扬杨
 */
@FeignClient(contextId = "remoteLogService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteLogFallbackFactory.class)
public interface RemoteLogService
{
    /**
     * 保存系统日志
     *
     * @param SysOperLogVO 日志实体
     * @param source 请求来源
     * @return 结果
     */
    @PostMapping("/operlog")
    public R<Boolean> saveLog(@RequestBody SysOperLogVO SysOperLogVO, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 保存访问记录
     *
     * @param SysLogininforVO 访问实体
     * @param source 请求来源
     * @return 结果
     */
    @PostMapping("/logininfor")
    public R<Boolean> saveLogininfor(@RequestBody SysLogininforVO SysLogininforVO, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
