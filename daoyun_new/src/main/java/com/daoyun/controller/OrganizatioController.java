package com.daoyun.controller;


import com.daoyun.entity.Dict;
import com.daoyun.entity.Organizatio;
import com.daoyun.entity.Result;
import com.daoyun.service.OrganizatioService;
import com.sun.org.apache.xpath.internal.operations.Or;
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
@RequestMapping("/organizatio")
public class OrganizatioController {
    @Resource
    private OrganizatioService organizatioService;

    @GetMapping("/search")
    @ApiOperation(value="展示所有父亲组织")
    @ApiImplicitParams({
    })
    public Result<List<Organizatio>> selectOrgParent(){
        Result<List<Organizatio>> result = new Result<>();
        List<Organizatio> organizatios = organizatioService.searchOrgAll();
        if(organizatios.isEmpty()){
            result.setCode(1);
            result.setStatus("找不到数据！");
            return result;
        }
        result.setCode(20000);
        result.setData(organizatios);
        return result;
    }

    @PostMapping("create")
    @ApiOperation(value="新增组织")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "name", value = "组织的名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "parentId", value = "所属于的组织", required = false, dataType = "int")
    })
    public Result<Organizatio> createOrg(Organizatio organizatio){
        Result<Organizatio> result = new Result<>();
        List<Organizatio> organizatios = organizatioService.searchOrgId(organizatio.getParentId());
        if(organizatios.isEmpty()&&organizatio.getParentId()!=0){
            result.setCode(1);
            result.setStatus("该父亲组织不存在");
            return result;
        }
        organizatioService.save(organizatio);
        result.setData(organizatio);
        result.setCode(20000);
        return result;
    }

    @PutMapping("update")
    @ApiOperation(value="修改组织")
    @ApiImplicitParams({
    })
    public Result<Organizatio> updateOrg(Organizatio organizatio){
        Result<Organizatio> result = new Result<>();
        organizatioService.updateById(organizatio);
        result.setCode(20000);
        result.setData(organizatio);
        return result;
    }

    @DeleteMapping("delete")
    @ApiOperation(value="删除组织")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "根据id进行删除", required = true, dataType = "int")
    })
    public Result deleteOrg(int id){
        Result result = new Result();
        organizatioService.removeById(id);
        result.setCode(20000);
        return result;
    }

    @GetMapping("/search/child")
    @ApiOperation(value="根据parentID查询组织")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "parentId", value = "所属于的组织", required = false, dataType = "int")
    })
    public Result<List<Organizatio>> selectOrgChild(int parentId){
        Result<List<Organizatio>> result = new Result<>();
        List<Organizatio> organizatios = organizatioService.searchOrgParent(parentId);
        if(organizatios.isEmpty()){
            result.setCode(1);
            result.setStatus("找不到数据！");
            return result;
        }
        result.setCode(20000);
        result.setData(organizatios);
        return result;
    }

    @GetMapping("/search/tree")
    @ApiOperation(value="树查询")
    @ApiImplicitParams({
    })
    public Result<List<Organizatio>> selectOrgTree(){
        Result<List<Organizatio>> result = new Result<>();
        List<Organizatio> organizatios = organizatioService.searchOrgTree();
        result.setCode(20000);
        result.setData(organizatios);
        return result;
    }
}

