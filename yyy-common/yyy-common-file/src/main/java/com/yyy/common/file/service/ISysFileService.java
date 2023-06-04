package com.yyy.common.file.service;

import com.yyy.common.core.domain.R;
import com.yyy.system.api.vo.SysFileVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传接口
 * 
* @author 羊扬杨
 */
public interface ISysFileService
{
    /**
     * 文件上传接口
     * 
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    public R<SysFileVO> uploadFile(MultipartFile file) throws Exception;
}
