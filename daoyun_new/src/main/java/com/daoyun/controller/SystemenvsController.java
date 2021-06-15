package com.daoyun.controller;


import com.daoyun.entity.Dict;
import com.daoyun.entity.Result;
import com.daoyun.entity.Systemenvs;
import com.daoyun.mapper.SystemenvsMapper;
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
@CrossOrigin
@RequestMapping("/systemenvs")
public class SystemenvsController {
    @Resource
    private SystemenvsService systemenvsService;
    @Resource
    private SystemenvsMapper systemenvsMapper;

    @GetMapping("/search")
    @ApiOperation(value="展示所有系统参数，分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "查系统参数的第几页", required = true, dataType = "int")
    })
    public Result<List<Systemenvs>> selectSys(@RequestParam int currentPage){
        Result<List<Systemenvs>> result = new Result<>();
        List<Systemenvs> systemenvsList = systemenvsService.selectAllSystemenvsByPage(currentPage);
        if (systemenvsList == null){
            result.setCode(0);
            result.setStatus("未查询到");
        }else {
            result.setCode(20000);
            result.setData(systemenvsService.selectAllSystemenvsByPage(currentPage));
        }
        return result;
    }


    @PostMapping("update")
    @ApiOperation(value="修改系统参数项")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "根据id选择修改哪个系统参数项", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "sysKey", value = "系统参数项关键字", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "sysValue", value = "系统参数项值", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "sysName", value = "系统参数项名字", required = true, dataType = "String")
    })
    public Result updateSys(@RequestParam int id,
                             @RequestParam String sysKey,
                             @RequestParam int sysValue,
                             @RequestParam String sysName){
        Systemenvs systemenvs = new Systemenvs();
        systemenvs.setId(id).setSysKey(sysKey).setSysValue(sysValue).setSysName(sysName);
        systemenvsService.updateSystemenvs(systemenvs);
        Result result = new Result();
        result.setCode(20000);
        return result;
    }

    @DeleteMapping("delete")
    @ApiOperation(value="删除系统参数项")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "根据id选择删除哪个系统参数", required = true, dataType = "int")
    })
    public Result deleteSys(@RequestParam int id){
        Result result = new Result();

        systemenvsService.deleteSystemenvs(id);

        result.setCode(20000);
        return result;
    }

    @PutMapping("create")
    @ApiOperation(value="创建系统参数项")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "sysKey", value = "系统参数项关键字", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "sysValue", value = "系统参数项值", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "sysName", value = "系统参数项名字", required = true, dataType = "String")
    })
    public Result createSys(@RequestParam String sysKey,
                            @RequestParam int sysValue,
                            @RequestParam String sysName){
        Result result = new Result();
        if(systemenvsService.keyIsExist(sysKey)){
            result.setCode(1);
            result.setStatus("关键字重复");
        }else {
            Systemenvs systemenvs = new Systemenvs();
            systemenvs.setSysKey(sysKey);
            systemenvs.setSysValue(sysValue);
            systemenvs.setSysName(sysName);
            systemenvsService.insertSystemenvs(systemenvs);
            result.setCode(20000);
        }
        return result;
    }
}

