package com.yyy.system.api.factory;

import com.yyy.system.api.vo.SysLogininforVO;
import com.yyy.system.api.vo.SysOperLogVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import com.yyy.common.core.domain.R;
import com.yyy.system.api.RemoteLogService;



/**
 * 日志服务降级处理
 * 
* @author 羊扬杨
 */
@Component
public class RemoteLogFallbackFactory implements FallbackFactory<RemoteLogService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteLogFallbackFactory.class);

    @Override
    public RemoteLogService create(Throwable throwable)
    {
        log.error("日志服务调用失败:{}", throwable.getMessage());
        return new RemoteLogService()
        {
            @Override
            public R<Boolean> saveLog(SysOperLogVO SysOperLogVO, String source)
            {
                return null;
            }

            @Override
            public R<Boolean> saveLogininfor(SysLogininforVO SysLogininforVO, String source)
            {
                return null;
            }
        };

    }
}
