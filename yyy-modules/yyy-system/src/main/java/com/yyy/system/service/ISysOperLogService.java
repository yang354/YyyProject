package com.yyy.system.service;

import java.util.List;
import com.yyy.system.api.vo.SysOperLogVO;

/**
 * 操作日志 服务层
 * 
* @author 羊扬杨
 */
public interface ISysOperLogService
{
    /**
     * 新增操作日志
     * 
     * @param operLog 操作日志对象
     * @return 结果
     */
    public int insertOperlog(SysOperLogVO operLog);

    /**
     * 查询系统操作日志集合
     * 
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    public List<SysOperLogVO> selectOperLogList(SysOperLogVO operLog);

    /**
     * 批量删除系统操作日志
     * 
     * @param operIds 需要删除的操作日志ID
     * @return 结果
     */
    public int deleteOperLogByIds(Long[] operIds);

    /**
     * 查询操作日志详细
     * 
     * @param operId 操作ID
     * @return 操作日志对象
     */
    public SysOperLogVO selectOperLogById(Long operId);

    /**
     * 清空操作日志
     */
    public void cleanOperLog();
}
