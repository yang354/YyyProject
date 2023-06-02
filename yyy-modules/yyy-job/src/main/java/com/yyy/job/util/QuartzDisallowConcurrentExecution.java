package com.yyy.job.util;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;

import com.yyy.job.vo.SysJobVO;

/**
 * 定时任务处理（禁止并发执行）
 * 
 * @author ruoyi
 *
 */
@DisallowConcurrentExecution
public class QuartzDisallowConcurrentExecution extends AbstractQuartzJob
{
    @Override
    protected void doExecute(JobExecutionContext context, SysJobVO sysJobVO) throws Exception
    {
        JobInvokeUtil.invokeMethod(sysJobVO);
    }
}
