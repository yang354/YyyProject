package com.yyy.gen.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyy.gen.domain.GenTableColumn;
import com.yyy.system.api.vo.SysDeptVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yyy.common.core.text.Convert;
import com.yyy.gen.vo.GenTableColumnVO;
import com.yyy.gen.mapper.GenTableColumnMapper;

/**
 * 业务字段 服务层实现
 * 
* @author 羊扬杨
 */
@Service
public class GenTableColumnServiceImpl extends ServiceImpl<GenTableColumnMapper, GenTableColumn> implements IGenTableColumnService
{
	@Autowired
	private GenTableColumnMapper genTableColumnMapper;

	/**
     * 查询业务字段列表
     * 
     * @param tableId 业务字段编号
     * @return 业务字段集合
     */
	@Override
	public List<GenTableColumnVO> selectGenTableColumnListByTableId(Long tableId)
	{
	    return genTableColumnMapper.selectGenTableColumnListByTableId(tableId);
	}
	
    /**
     * 新增业务字段
     * 
     * @param GenTableColumnVO 业务字段信息
     * @return 结果
     */
	@Override
	public int insertGenTableColumn(GenTableColumnVO GenTableColumnVO)
	{
	    return genTableColumnMapper.insertGenTableColumn(GenTableColumnVO);
	}
	
	/**
     * 修改业务字段
     * 
     * @param GenTableColumnVO 业务字段信息
     * @return 结果
     */
	@Override
	public int updateGenTableColumn(GenTableColumnVO GenTableColumnVO)
	{
	    return genTableColumnMapper.updateGenTableColumn(GenTableColumnVO);
	}

	/**
     * 删除业务字段对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteGenTableColumnByIds(String ids)
	{
		return genTableColumnMapper.deleteGenTableColumnByIds(Convert.toLongArray(ids));
	}
}