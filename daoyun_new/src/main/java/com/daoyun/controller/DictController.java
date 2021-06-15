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
@CrossOrigin
@RequestMapping("/dict")
public class DictController {
    @Resource
    private DictService dictService;

    @GetMapping("/search/parent")
    @ApiOperation(value="展示所有数据字典父亲层（父亲）")
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
            result.setCode(20000);
            result.setData(dicts);
        }
        return result;
    }

    @GetMapping("/search/Child")
    @ApiOperation(value="展示所属名字父亲层的儿子层（儿子）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "查询数据字典项", required = true, dataType = "String")
    })
    public Result<List<Dict>> selectDictByName(@RequestParam String name){
        Result<List<Dict>> result = new Result<>();
        List<Dict> dicts = dictService.selectDictByName(name);
        if (dicts == null){
            result.setCode(0);
            result.setStatus("未查询到");
        }else {
            result.setCode(20000);
            result.setData(dicts);
        }
        return result;
    }

    @GetMapping("/search")
    @ApiOperation(value="展示所有数据字典项")
    @ApiImplicitParams({
    })
    public Result<List<Dict>> selectAllDict(){
        Result<List<Dict>> result = new Result<>();
        List<Dict> dicts = dictService.selectAllDict();
        if (dicts == null){
            result.setCode(0);
            result.setStatus("未查询到");
        }else {
            result.setCode(20000);
            result.setData(dicts);
        }
        return result;
    }

    @PutMapping("create")
    @ApiOperation(value="新增数据字典项")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "name", value = "字典项的名字", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "englishName", value = "字典项的英文名字", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "description", value = "字典项的描述", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "defaultValue", value = "字典项是否有默认值", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "value", value = "字典项的默认值", required = true, dataType = "String")
    })
    public Result createDict(@RequestParam String name,
                             @RequestParam String englishName,
                             @RequestParam String description,
                             @RequestParam int defaultValue,
                             @RequestParam String value
    ){
        Result result = new Result();
        Dict dict = new Dict();
        dict.setName(name).setEnglishName(englishName).setDescription(description)
                .setDefaultValue(defaultValue).setValue(value);
        dictService.insertDict(dict);
        result.setCode(20000);
        return result;
    }

    @PostMapping("update/child")
    @ApiOperation(value="根据ID修改数据字典项（儿子）")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "根据id选择修改哪个字典项", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "description", value = "字典项的描述", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "value", value = "字典项的值", required = true, dataType = "String")
    })
    public Result updateDictById(@RequestParam int id,
                             @RequestParam String description,
                             @RequestParam String value){
        Dict dict = new Dict();
        dict.setId(id).setDescription(description).setValue(value);
        dictService.updateDict(dict);
        Result result = new Result();
        result.setCode(20000);
        return result;
    }

    @PostMapping("update/parent")
    @ApiOperation(value="根据名字修改数据字典项（父亲）")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "oldName", value = "要修改的父亲字典项的名字", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "name", value = "字典项的名字", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "englishName", value = "字典项的英文名字", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "defaultValue", value = "字典项默认值", required = true, dataType = "int")
    })
    public Result updateDictByName(@RequestParam String oldName,
                                   @RequestParam String name,
                                   @RequestParam String englishName,
                                   @RequestParam int defaultValue){
        dictService.updateDictByName(oldName,name,englishName,defaultValue);
        Result result = new Result();
        result.setCode(20000);
        return result;
    }

    @DeleteMapping("delete/child")
    @ApiOperation(value="删除数据字典项(儿子)")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "根据id进行删除", required = true, dataType = "int")
    })
    public Result deleteDict(@RequestParam int id){
        dictService.deleteDict(id);
        Result result = new Result();
        result.setCode(20000);
        return result;
    }

    @DeleteMapping("delete/parent")
    @ApiOperation(value="删除数据字典项（父亲）")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "name", value = "根据name进行删除", required = true, dataType = "String")
    })
    public Result deleteDict(@RequestParam String name){
        dictService.deleteDictByName(name);
        Result result = new Result();
        result.setCode(20000);
        return result;
    }
}

