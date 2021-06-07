package com.daoyun.controller;


import com.daoyun.entity.Dict;
import com.daoyun.entity.Result;
import com.daoyun.service.DictService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/dict")
public class DictController {
    @Resource
    private DictService dictService;

    @GetMapping("/dicts")
    @ApiOperation(value="展示所有数据字典项，分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "查字典表的第几页", required = true, dataType = "int")
    })
    public Result<List<Dict>> selectDict(@RequestParam int currentPage){
        Result<List<Dict>> result = new Result<>();
        List<Dict> dicts = dictService.selectAllDictByPage(currentPage);
        if (dicts == null){
            result.setCode(0);
            result.setStatus("未查询到");
        }else {
            result.setCode(2000);
            result.setData(dictService.selectAllDictByPage(currentPage));
        }
        return result;
    }

    @PostMapping("insert")
    @ApiOperation(value="新增数据字典项")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "name", value = "字典项的名字", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "englishName", value = "字典项的英文名字", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "description", value = "字典项的描述", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "defaultValue", value = "字典项是否有默认值", required = true, dataType = "Boolean"),
            @ApiImplicitParam(paramType="query", name = "value", value = "字典项的默认值", required = true, dataType = "String")
    })
    public Result createDict(@RequestParam String name,
                             @RequestParam String englishName,
                             @RequestParam String description,
                             @RequestParam Boolean defaultValue,
                             @RequestParam String value
    ){
        Result result = new Result();
        Dict dict = new Dict();
        dict.setName(name).setEnglishName(englishName).setDescription(description)
                .setDefaultValue(defaultValue).setValue(value);
        dictService.insertDict(dict);
        result.setCode(2000);
        return result;
    }

    @PostMapping("update")
    @ApiOperation(value="修改数据字典项")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "根据id选择修改哪个字典项", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "name", value = "字典项的名字", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "englishName", value = "字典项的英文名字", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "description", value = "字典项的描述", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "defaultValue", value = "字典项是否有默认值", required = true, dataType = "Boolean"),
            @ApiImplicitParam(paramType="query", name = "value", value = "字典项的默认值", required = true, dataType = "String")
    })
    public Result updateDict(@RequestParam int id,
                             @RequestParam String name,
                             @RequestParam String englishName,
                             @RequestParam String description,
                             @RequestParam Boolean defaultValue,
                             @RequestParam String value){
        Dict dict = new Dict();
        dict.setId(id).setName(name).setEnglishName(englishName).setDescription(description)
                .setDefaultValue(defaultValue).setValue(value);
        dictService.updateDict(dict);
        Result result = new Result();
        result.setCode(2000);
        return result;
    }

    @PostMapping("delete")
    @ApiOperation(value="删除数据字典项")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "根据id进行删除", required = true, dataType = "int")
    })
    public Result deleteDict(@RequestParam int id){
        dictService.deleteDict(id);
        Result result = new Result();
        result.setCode(2000);
        return result;
    }
}

