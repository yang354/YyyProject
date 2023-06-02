package com.yyy.job.mapper;

import java.util.List;

import com.yyy.common.core.web.page.BaseMapperPlus;
import com.yyy.job.domain.SysJob;
import com.yyy.job.domain.SysJobLog;
import com.yyy.job.vo.SysJobVO;

/**
 * 调度任务信息 数据层
 * 
* @author 羊扬杨
 */
public interface SysJobMapper extends BaseMapperPlus<SysJob>
{
    /**
     * 查询调度任务日志集合
     * 
     * @param job 调度信息
     * @return 操作日志集合
     */
    public List<SysJobVO> selectJobList(SysJobVO job);

    /**
     * 查询所有调度任务
     * 
     * @return 调度任务列表
     */
    public List<SysJobVO> selectJobAll();

    /**
     * 通过调度ID查询调度任务信息
     * 
     * @param jobId 调度ID
     * @return 角色对象信息
     */
    public SysJobVO selectJobById(Long jobId);

    /**
     * 通过调度ID删除调度任务信息
     * 
     * @param jobId 调度ID
     * @return 结果
     */
    public int deleteJobById(Long jobId);

    /**
     * 批量删除调度任务信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteJobByIds(Long[] ids);

    /**
     * 修改调度任务信息
     * 
     * @param job 调度任务信息
     * @return 结果
     */
    public int updateJob(SysJobVO job);

    /**
     * 新增调度任务信息
     * 
     * @param job 调度任务信息
     * @return 结果
     */
    public int insertJob(SysJobVO job);
}
