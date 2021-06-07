package com.daoyun.controller;


import com.daoyun.entity.Dict;
import com.daoyun.entity.Result;
import com.daoyun.entity.Systemenvs;
import com.daoyun.service.DictService;
import com.daoyun.service.SystemenvsService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-25
 */
@RestController
@RequestMapping("/systemenvs")
public class SystemenvsController {
    @Resource
    private SystemenvsService systemenvsService;

    @GetMapping("/systemenvsServices")
    @ApiOperation(value="展示所有系统参数，分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "查字典表的第几页", required = true, dataType = "int")
    })
    public Result<List<Systemenvs>> selectDict(@RequestParam int currentPage){
        Result<List<Systemenvs>> result = new Result<>();
        List<Systemenvs> systemenvsList = systemenvsService.selectAllSystemenvsByPage(currentPage);
        if (systemenvsList == null){
            result.setCode(0);
            result.setStatus("未查询到");
        }else {
            result.setCode(2000);
            result.setData(systemenvsService.selectAllSystemenvsByPage(currentPage));
        }
        return result;
    }


    @PostMapping("update")
    @ApiOperation(value="修改系统参数项")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "根据id选择修改哪个字典项", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "experience", value = "签到经验值", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "distance", value = "签到距离限制", required = true, dataType = "int")
    })
    public Result updateDict(@RequestParam int id,
                             @RequestParam int experience,
                             @RequestParam int distance){
        Systemenvs systemenvs = new Systemenvs();
        systemenvs.setId(id).setExperience(experience).setDistance(distance);
        systemenvsService.updateSystemenvs(systemenvs);
        Result result = new Result();
        result.setCode(2000);
        return result;
    }
}

