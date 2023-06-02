package com.yyy.job.util;

import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.yyy.common.core.constant.ScheduleConstants;
import com.yyy.common.core.utils.ExceptionUtil;
import com.yyy.common.core.utils.SpringUtils;
import com.yyy.common.core.utils.StringUtils;
import com.yyy.common.core.utils.bean.BeanUtils;
import com.yyy.job.vo.SysJobVO;
import com.yyy.job.vo.SysJobLogVO;
import com.yyy.job.service.ISysJobLogService;

/**
 * 抽象quartz调用
 *
* @author 羊扬杨
 */
public abstract class AbstractQuartzJob implements Job
{
    private static final Logger log = LoggerFactory.getLogger(AbstractQuartzJob.class);

    /**
     * 线程本地变量
     */
    private static ThreadLocal<Date> threadLocal = new ThreadLocal<>();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        SysJobVO SysJobVO = new SysJobVO();
        BeanUtils.copyBeanProp(SysJobVO, context.getMergedJobDataMap().get(ScheduleConstants.TASK_PROPERTIES));
        try
        {
            before(context, SysJobVO);
            if (SysJobVO != null)
            {
                doExecute(context, SysJobVO);
            }
            after(context, SysJobVO, null);
        }
        catch (Exception e)
        {
            log.error("任务执行异常  - ：", e);
            after(context, SysJobVO, e);
        }
    }

    /**
     * 执行前
     *
     * @param context 工作执行上下文对象
     * @param sysJobVO 系统计划任务
     */
    protected void before(JobExecutionContext context, SysJobVO sysJobVO)
    {
        threadLocal.set(new Date());
    }

    /**
     * 执行后
     *
     * @param context 工作执行上下文对象
     * @param sysJobVO 系统计划任务
     */
    protected void after(JobExecutionContext context, SysJobVO sysJobVO, Exception e)
    {
        Date startTime = threadLocal.get();
        threadLocal.remove();

        final SysJobLogVO SysJobLogVO = new SysJobLogVO();
        SysJobLogVO.setJobName(sysJobVO.getJobName());
        SysJobLogVO.setJobGroup(sysJobVO.getJobGroup());
        SysJobLogVO.setInvokeTarget(sysJobVO.getInvokeTarget());
        SysJobLogVO.setStartTime(startTime);
        SysJobLogVO.setStopTime(new Date());
        long runMs = SysJobLogVO.getStopTime().getTime() - SysJobLogVO.getStartTime().getTime();
        SysJobLogVO.setJobMessage(SysJobLogVO.getJobName() + " 总共耗时：" + runMs + "毫秒");
        if (e != null)
        {
            SysJobLogVO.setStatus("1");
            String errorMsg = StringUtils.substring(ExceptionUtil.getExceptionMessage(e), 0, 2000);
            SysJobLogVO.setExceptionInfo(errorMsg);
        }
        else
        {
            SysJobLogVO.setStatus("0");
        }

        // 写入数据库当中
        SpringUtils.getBean(ISysJobLogService.class).addJobLog(SysJobLogVO);
    }

    /**
     * 执行方法，由子类重载
     *
     * @param context 工作执行上下文对象
     * @param sysJobVO 系统计划任务
     * @throws Exception 执行过程中的异常
     */
    protected abstract void doExecute(JobExecutionContext context, SysJobVO sysJobVO) throws Exception;
}
