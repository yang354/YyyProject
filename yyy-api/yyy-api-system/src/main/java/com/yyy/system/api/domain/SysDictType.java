package com.yyy.system.api.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.yyy.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author yzz
 * @Date 2023/6/1 下午6:36
 */
@Data
@TableName("sys_dict_type")
public class SysDictType implements Serializable {

        private static final long serialVersionUID = 1L;

        /** 字典主键 */
        @Excel(name = "字典主键", cellType = Excel.ColumnType.NUMERIC)
        private Long dictId;

        /** 字典名称 */
        @Excel(name = "字典名称")
        private String dictName;

        /** 字典类型 */
        @Excel(name = "字典类型")
        private String dictType;

        /** 状态（0正常 1停用） */
        @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
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
