package com.yyy.job.util;

import org.quartz.JobExecutionContext;

import com.yyy.job.vo.SysJobVO;

/**
 * 定时任务处理（允许并发执行）
 * 
 * @author ruoyi
 *
 */
public class QuartzJobExecution extends AbstractQuartzJob
{
    @Override
    protected void doExecute(JobExecutionContext context, SysJobVO sysJobVO) throws Exception
    {
        JobInvokeUtil.invokeMethod(sysJobVO);
    }
}
