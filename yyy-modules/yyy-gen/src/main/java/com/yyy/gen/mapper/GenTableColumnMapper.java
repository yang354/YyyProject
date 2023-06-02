package com.yyy.gen.mapper;

import java.util.List;

import com.yyy.common.core.web.page.BaseMapperPlus;
import com.yyy.gen.domain.GenTableColumn;
import com.yyy.gen.vo.GenTableColumnVO;

/**
 * 业务字段 数据层
 * 
* @author 羊扬杨
 */
public interface GenTableColumnMapper extends BaseMapperPlus<GenTableColumn>
{
    /**
     * 根据表名称查询列信息
     * 
     * @param tableName 表名称
     * @return 列信息
     */
    public List<GenTableColumnVO> selectDbTableColumnsByName(String tableName);

    /**
     * 查询业务字段列表
     * 
     * @param tableId 业务字段编号
     * @return 业务字段集合
     */
    public List<GenTableColumnVO> selectGenTableColumnListByTableId(Long tableId);

    /**
     * 新增业务字段
     * 
     * @param GenTableColumnVO 业务字段信息
     * @return 结果
     */
    public int insertGenTableColumn(GenTableColumnVO GenTableColumnVO);

    /**
     * 修改业务字段
     * 
     * @param GenTableColumnVO 业务字段信息
     * @return 结果
     */
    public int updateGenTableColumn(GenTableColumnVO GenTableColumnVO);

    /**
     * 删除业务字段
     * 
     * @param genTableColumns 列数据
     * @return 结果
     */
    public int deleteGenTableColumns(List<GenTableColumnVO> genTableColumns);

    /**
     * 批量删除业务字段
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGenTableColumnByIds(Long[] ids);
}