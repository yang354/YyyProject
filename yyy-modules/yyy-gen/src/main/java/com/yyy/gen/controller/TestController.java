package com.yyy.gen.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyy.common.core.domain.R;
import com.yyy.gen.domain.GenTableColumn;
import com.yyy.gen.mapper.GenTableColumnMapper;
import com.yyy.gen.service.IGenTableColumnService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author yzz
 * @Date 2023/6/1 下午3:04
 */

@RequestMapping("/test")
@RestController
public class TestController {
    @Autowired
    private GenTableColumnMapper genTableColumnMapper;
    @Resource
    private IGenTableColumnService iGenTableColumnService;

    /**
     * 测试mybatis-plus新增
     */

    @GetMapping("/inster")
    public R inster()
    {
        QueryWrapper<GenTableColumn> sysDeptQueryWrapper = new QueryWrapper<>();
        sysDeptQueryWrapper.eq("column_id",1l);
        GenTableColumn SysDeptVO = iGenTableColumnService.getOne(sysDeptQueryWrapper);
        return R.ok(SysDeptVO);
    }
}
