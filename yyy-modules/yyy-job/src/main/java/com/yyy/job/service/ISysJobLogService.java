package com.yyy.job.service;

import java.util.List;

import com.yyy.common.core.web.page.IServicePlus;
import com.yyy.job.domain.SysJobLog;
import com.yyy.job.vo.SysJobLogVO;

/**
 * 定时任务调度日志信息信息 服务层
 * 
* @author 羊扬杨
 */
public interface ISysJobLogService extends IServicePlus<SysJobLog>
{
    /**
     * 获取quartz调度器日志的计划任务
     * 
     * @param jobLog 调度日志信息
     * @return 调度任务日志集合
     */
    public List<SysJobLogVO> selectJobLogList(SysJobLogVO jobLog);

    /**
     * 通过调度任务日志ID查询调度信息
     * 
     * @param jobLogId 调度任务日志ID
     * @return 调度任务日志对象信息
     */
    public SysJobLogVO selectJobLogById(Long jobLogId);

    /**
     * 新增任务日志
     * 
     * @param jobLog 调度日志信息
     */
    public void addJobLog(SysJobLogVO jobLog);

    /**
     * 批量删除调度日志信息
     * 
     * @param logIds 需要删除的日志ID
     * @return 结果
     */
    public int deleteJobLogByIds(Long[] logIds);

    /**
     * 删除任务日志
     * 
     * @param jobId 调度日志ID
     * @return 结果
     */
    public int deleteJobLogById(Long jobId);

    /**
     * 清空任务日志
     */
    public void cleanJobLog();
}
