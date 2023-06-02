package com.yyy.job.domain;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.yyy.common.core.annotation.Excel;
import com.yyy.common.core.annotation.Excel.ColumnType;
import com.yyy.common.core.constant.ScheduleConstants;
import com.yyy.common.core.utils.StringUtils;
import com.yyy.common.core.web.domain.BaseEntity;
import com.yyy.job.util.CronUtils;

/**
 * 定时任务调度表 sys_job
 * 
* @author 羊扬杨
 */
@Data
@TableName("sys_job")
public class SysJob implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 任务ID */
    @Excel(name = "任务序号", cellType = ColumnType.NUMERIC)
    private Long jobId;

    /** 任务名称 */
    @Excel(name = "任务名称")
    private String jobName;

    /** 任务组名 */
    @Excel(name = "任务组名")
    private String jobGroup;

    /** 调用目标字符串 */
    @Excel(name = "调用目标字符串")
    private String invokeTarget;

    /** cron执行表达式 */
    @Excel(name = "执行表达式 ")
    private String cronExpression;

    /** cron计划策略 */
    @Excel(name = "计划策略 ", readConverterExp = "0=默认,1=立即触发执行,2=触发一次执行,3=不触发立即执行")
    private String misfirePolicy = ScheduleConstants.MISFIRE_DEFAULT;

    /**
     是否并发执行（0允许 1禁止）
     多个定时任务使用的是同一个调度线程，所以定时任务是阻塞执行的，执行效率不高。
     比如20:00的任务需要执行2个小时，而恰好你在21:00需要执行另一个任务，那在21:00的任务会被阻塞到22:00进行，可能会导致执行结果不是我们想要的。
     这是是否并发执行就起到了作用
     */
    @Excel(name = "并发执行", readConverterExp = "0=允许,1=禁止")
    private String concurrent;

    /** 任务状态（0正常 1暂停） */
    @Excel(name = "任务状态", readConverterExp = "0=正常,1=暂停")
    private String status;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 备注 */
    private String remark;
}