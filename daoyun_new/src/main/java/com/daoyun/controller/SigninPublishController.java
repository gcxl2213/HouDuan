package com.daoyun.controller;


import com.daoyun.entity.Result;
import com.daoyun.service.DictService;
import com.daoyun.service.SigninPublishService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-25
 */
@RestController
@RequestMapping("/signinPublish")
public class SigninPublishController {
    @Resource
    private SigninPublishService signinPublishService;

    @PostMapping("insert")
    @ApiOperation(value="创建新的签到")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "name", value = "字典项的名字", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "englishName", value = "字典项的英文名字", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "description", value = "字典项的描述", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "defaultValue", value = "字典项是否有默认值", required = true, dataType = "Boolean"),
            @ApiImplicitParam(paramType="query", name = "value", value = "字典项的默认值", required = true, dataType = "String")
    })
    public Result createSignin(){
        Result result = new Result();
        return result;
    }

}

