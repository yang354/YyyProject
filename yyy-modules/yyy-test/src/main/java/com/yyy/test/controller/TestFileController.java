package com.yyy.test.controller;

import com.yyy.common.core.domain.R;
import com.yyy.common.file.service.ISysFileService;
import com.yyy.system.api.vo.SysFileVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @Author yzz
 * @Date 2023/6/10 上午12:14
 */

@Api(tags = {"测试文件功能"})
@Slf4j
@RestController
@RequestMapping("/file")
public class TestFileController {

    @Value("${fileTemplate.profile}")
    private String profilePath;


    @Autowired
    private ISysFileService sysFileService;

    /**
     * 文件上传请求   @RequestPart使用了这个注解，swagger的ui上就出现了文件选择框
     */
    @ApiOperation("测试文件上传请求")
    @PostMapping("testUpload")
    public R<SysFileVO> upload(@RequestPart(value = "file") MultipartFile file)
    {
        try
        {
            // 上传并返回访问地址
            R<SysFileVO> sysFile = sysFileService.uploadFile(file);

            return sysFile;
        }
        catch (Exception e)
        {
            log.error("上传文件失败", e);
            return R.fail(e.getMessage());
        }
    }


    /**
     * 测试获取项目本地文件
     */
    @ApiOperation("测试获取项目本地文件")
    @GetMapping("/testProjectLocalFile")
    public R getFileTemplate(){
        File file1 = new File(profilePath);

        System.out.println(file1.length()+"~~~~~~~~~~"+file1.getName());
        return R.ok("获取成功");
    }

}
