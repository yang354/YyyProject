package com.yyy.system.mapper;

import java.util.List;

import com.yyy.common.core.web.page.BaseMapperPlus;
import com.yyy.system.domain.SysMenu;
import com.yyy.system.domain.SysNotice;
import com.yyy.system.vo.SysNoticeVO;

/**
 * 通知公告表 数据层
 * 
* @author 羊扬杨
 */
public interface SysNoticeMapper extends BaseMapperPlus<SysNotice>
{
    /**
     * 查询公告信息
     * 
     * @param noticeId 公告ID
     * @return 公告信息
     */
    public SysNoticeVO selectNoticeById(Long noticeId);

    /**
     * 查询公告列表
     * 
     * @param notice 公告信息
     * @return 公告集合
     */
    public List<SysNoticeVO> selectNoticeList(SysNoticeVO notice);

    /**
     * 新增公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    public int insertNotice(SysNoticeVO notice);

    /**
     * 修改公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    public int updateNotice(SysNoticeVO notice);

    /**
     * 批量删除公告
     * 
     * @param noticeId 公告ID
     * @return 结果
     */
    public int deleteNoticeById(Long noticeId);

    /**
     * 批量删除公告信息
     * 
     * @param noticeIds 需要删除的公告ID
     * @return 结果
     */
    public int deleteNoticeByIds(Long[] noticeIds);
}