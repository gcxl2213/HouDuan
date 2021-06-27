package com.daoyun.controller;


import com.daoyun.entity.Dict;
import com.daoyun.entity.DictSub;
import com.daoyun.entity.Result;
import com.daoyun.service.DictService;
import com.daoyun.service.DictSubService;
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
    @Resource
    private DictSubService dictSubService;

    @GetMapping("/search/main")
    @ApiOperation(value="展示所有数据字典/父亲")
    @ApiImplicitParams({
    })
    public Result<List<Dict>> selectDictMain(){
        Result<List<Dict>> result = new Result<>();
        List<Dict> dicts = dictService.selectAllDict();
        if (dicts.isEmpty()){
            result.setCode(1);
            result.setStatus("未查询到");
        }else {
            result.setCode(20000);
            result.setData(dicts);
        }
        return result;
    }

    @PostMapping("create/main")
    @ApiOperation(value="新增数据字典项/父亲")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "name", value = "字典项的名字", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "englishName", value = "字典项的英文名字", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "description", value = "字典项的描述", required = false, dataType = "String")
    })
    public Result<Dict> createDictMain(Dict dict){
        Result<Dict> result = new Result<>();
        List<Dict> dicts = dictService.selectDictByName(dict.getName());
        if(!dicts.isEmpty()){
            result.setCode(1);
            result.setStatus("该数据字典项已经存在");
            return result;
        }
        dictService.save(dict);
        result.setCode(20000);
        return result;
    }

    @PutMapping("update/main")
    @ApiOperation(value="修改数据字典项/父亲")
    @ApiImplicitParams({
    })
    public Result<Dict> updateDictMain(Dict dict){
        Result<Dict> result = new Result<>();
        dictService.updateById(dict);
        result.setCode(20000);
        result.setData(dict);
        return result;
    }

    @DeleteMapping("delete/parent")
    @ApiOperation(value="删除数据字典项/父亲")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "根据id进行删除", required = true, dataType = "int")
    })
    public Result deleteDictMain(int id){
        Result result = new Result();
        dictService.removeById(id);
        result.setCode(20000);
        return result;
    }

    @GetMapping("/search/sub")
    @ApiOperation(value="展示所有数据字典/儿子")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "parentId", value = "根据父项查询儿子", required = true, dataType = "int")
    })
    public Result<List<DictSub>> selectDictSub(int parentId){
        Result<List<DictSub>> result = new Result<>();
        List<DictSub> dictSubs = dictSubService.searchDictSubByParentId(parentId);
        if (dictSubs.isEmpty()){
            result.setCode(1);
            result.setStatus("未查询到");
        }else {
            result.setCode(20000);
            result.setData(dictSubs);
        }
        return result;
    }

    @PostMapping("create/sub")
    @ApiOperation(value="根据parentid新增数据字典项/儿子")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "description", value = "字典项的描述", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "defaultValue", value = "是否拥有默认值", required = true, dataType = "boolean"),
            @ApiImplicitParam(paramType="query", name = "value", value = "字典的值", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "parentId", value = "所属父表", required = true, dataType = "int")
    })
    public Result<DictSub> createDictSub(DictSub dictSub){
        Result<DictSub> result = new Result<>();
        Dict dict = dictService.getById(dictSub.getParentId());
        if(dict == null){
            result.setCode(1);
            result.setStatus("不存在该父项");
            return result;
        }
        List<DictSub> dictSubs = dictSubService.searchDictSubByParentDefault(dictSub.getParentId());
        if(!dictSubs.isEmpty() && dictSub.isDefaultValue()){
            dictSub.setDefaultValue(false);
            result.setStatus("已经存在默认值，帮您将此子项默认值改为false！");
        }
        dictSubService.save(dictSub);
        result.setCode(20000);
        result.setData(dictSub);
        return result;
    }

    @PutMapping("update/sub")
    @ApiOperation(value="修改数据字典项/儿子")
    @ApiImplicitParams({
    })
    public Result<DictSub> updateDictSub(DictSub dictSub){
        Result<DictSub> result = new Result<>();
        List<DictSub> dictSubs = dictSubService.searchDictSubByParentDefault(dictSub.getParentId());
        if(dictSub.isDefaultValue() && !dictSubs.isEmpty()){
            DictSub dictSub1 = dictSubs.get(0);
            dictSub1.setDefaultValue(false);
            dictSubService.updateById(dictSub1);
            result.setStatus("已经存在默认值，帮您将其他子项默认值取消！");
        }
        dictSubService.updateById(dictSub);
        result.setCode(20000);
        result.setData(dictSub);
        return result;
    }

    @DeleteMapping("delete/sub")
    @ApiOperation(value="删除数据字典项/儿子")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "根据id进行删除", required = true, dataType = "int")
    })
    public Result deleteDictSub(int id){
        Result result = new Result();
        dictSubService.removeById(id);
        result.setCode(20000);
        return result;
    }

    @GetMapping("/search/same")
    @ApiOperation(value="查找是否存在同父亲value的相同值")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "parentId", value = "根据父项查询儿子", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "value", value = "值", required = true, dataType = "int")
    })
    public Result<Boolean> isSame(int parentId, int value){
        Result<Boolean> result = new Result<>();
        List<DictSub> dictSubs = dictSubService.searchDictSubByParentValue(parentId, value);
        if (!dictSubs.isEmpty()){
            result.setCode(20000);
            result.setData(true);
        }else {
            result.setCode(20000);
            result.setData(false);
        }
        return result;
    }

    @GetMapping("/search/max")
    @ApiOperation(value="查找同父亲value的最大值")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "parentId", value = "根据父项查询儿子", required = true, dataType = "int")
    })
    public Result<Integer> searchmax(int parentId){
        Result<Integer> result = new Result<>();
        Integer maxNum = dictSubService.searchDictSubByParentMax(parentId);
        result.setData(maxNum);
        result.setCode(20000);
        return result;
    }
}

