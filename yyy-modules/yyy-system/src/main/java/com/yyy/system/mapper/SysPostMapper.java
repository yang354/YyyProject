package com.yyy.system.mapper;

import java.util.List;

import com.yyy.common.core.web.page.BaseMapperPlus;
import com.yyy.system.api.domain.SysOperLog;
import com.yyy.system.domain.SysPost;
import com.yyy.system.vo.SysPostVO;

/**
 * 岗位信息 数据层
 * 
* @author 羊扬杨
 */
public interface SysPostMapper extends BaseMapperPlus<SysPost>
{
    /**
     * 查询岗位数据集合
     * 
     * @param post 岗位信息
     * @return 岗位数据集合
     */
    public List<SysPostVO> selectPostList(SysPostVO post);

    /**
     * 查询所有岗位
     * 
     * @return 岗位列表
     */
    public List<SysPostVO> selectPostAll();

    /**
     * 通过岗位ID查询岗位信息
     * 
     * @param postId 岗位ID
     * @return 角色对象信息
     */
    public SysPostVO selectPostById(Long postId);

    /**
     * 根据用户ID获取岗位选择框列表
     * 
     * @param userId 用户ID
     * @return 选中岗位ID列表
     */
    public List<Long> selectPostListByUserId(Long userId);

    /**
     * 查询用户所属岗位组
     * 
     * @param userName 用户名
     * @return 结果
     */
    public List<SysPostVO> selectPostsByUserName(String userName);

    /**
     * 删除岗位信息
     * 
     * @param postId 岗位ID
     * @return 结果
     */
    public int deletePostById(Long postId);

    /**
     * 批量删除岗位信息
     * 
     * @param postIds 需要删除的岗位ID
     * @return 结果
     */
    public int deletePostByIds(Long[] postIds);

    /**
     * 修改岗位信息
     * 
     * @param post 岗位信息
     * @return 结果
     */
    public int updatePost(SysPostVO post);

    /**
     * 新增岗位信息
     * 
     * @param post 岗位信息
     * @return 结果
     */
    public int insertPost(SysPostVO post);

    /**
     * 校验岗位名称
     * 
     * @param postName 岗位名称
     * @return 结果
     */
    public SysPostVO checkPostNameUnique(String postName);

    /**
     * 校验岗位编码
     * 
     * @param postCode 岗位编码
     * @return 结果
     */
    public SysPostVO checkPostCodeUnique(String postCode);
}
