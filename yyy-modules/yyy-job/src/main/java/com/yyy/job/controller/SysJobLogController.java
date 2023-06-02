package com.yyy.job.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.yyy.common.core.utils.poi.ExcelUtil;
import com.yyy.common.core.web.controller.BaseController;
import com.yyy.common.core.web.domain.AjaxResult;
import com.yyy.common.core.web.page.TableDataInfo;
import com.yyy.common.log.annotation.Log;
import com.yyy.common.log.enums.BusinessType;
import com.yyy.common.security.annotation.RequiresPermissions;
import com.yyy.job.vo.SysJobLogVO;
import com.yyy.job.service.ISysJobLogService;

/**
 * 调度日志操作处理
 * 
* @author 羊扬杨
 */
@RestController
@RequestMapping("/job/log")
public class SysJobLogController extends BaseController
{
    @Autowired
    private ISysJobLogService jobLogService;

    /**
     * 查询定时任务调度日志列表
     */
    @RequiresPermissions("monitor:job:list")
    @GetMapping("/list")
    public TableDataInfo list(SysJobLogVO sysJobLogVO)
    {
        startPage();
        List<SysJobLogVO> list = jobLogService.selectJobLogList(sysJobLogVO);
        return getDataTable(list);
    }

    /**
     * 导出定时任务调度日志列表
     */
    @RequiresPermissions("monitor:job:export")
    @Log(title = "任务调度日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysJobLogVO sysJobLogVO)
    {
        List<SysJobLogVO> list = jobLogService.selectJobLogList(sysJobLogVO);
        ExcelUtil<SysJobLogVO> util = new ExcelUtil<SysJobLogVO>(SysJobLogVO.class);
        util.exportExcel(response, list, "调度日志");
    }

    /**
     * 根据调度编号获取详细信息
     */
    @RequiresPermissions("monitor:job:query")
    @GetMapping(value = "/{jobLogId}")
    public AjaxResult getInfo(@PathVariable Long jobLogId)
    {
        return success(jobLogService.selectJobLogById(jobLogId));
    }

    /**
     * 删除定时任务调度日志
     */
    @RequiresPermissions("monitor:job:remove")
    @Log(title = "定时任务调度日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{jobLogIds}")
    public AjaxResult remove(@PathVariable Long[] jobLogIds)
    {
        return toAjax(jobLogService.deleteJobLogByIds(jobLogIds));
    }

    /**
     * 清空定时任务调度日志
     */
    @RequiresPermissions("monitor:job:remove")
    @Log(title = "调度日志", businessType = BusinessType.CLEAN)
    @DeleteMapping("/clean")
    public AjaxResult clean()
    {
        jobLogService.cleanJobLog();
        return success();
    }
}
