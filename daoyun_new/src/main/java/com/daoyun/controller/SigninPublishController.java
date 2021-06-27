package com.daoyun.controller;


import com.daoyun.entity.Course;
import com.daoyun.entity.Result;
import com.daoyun.entity.SigninPublish;
import com.daoyun.mapper.SigninPublishMapper;
import com.daoyun.service.CourseService;
import com.daoyun.service.DictService;
import com.daoyun.service.SigninPublishService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.jdbc.Null;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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
@CrossOrigin
@RequestMapping("/signinPublish")
public class SigninPublishController {
    @Resource
    private CourseService courseService;
    @Resource
    private SigninPublishService signinPublishService;

    @PostMapping("create")
    @ApiOperation(value="创建新的签到")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "courseId", value = "签到所属班课", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "latitude", value = "签到纬度", required = true, dataType = "float"),
            @ApiImplicitParam(paramType="query", name = "longitude", value = "签到经度", required = true, dataType = "float")
    })
    public Result<SigninPublish> createSignin(SigninPublish signinPublish){
        Result<SigninPublish> signinPublishResult = new Result<>();
        //1、根据courseId查询这门班课是否已经存在签到。
        Course course = courseService.searchCourseById(signinPublish.getCourseId());
        if(course.getCurrentSignin()!= -1){
            signinPublishResult.setCode(1);
            signinPublishResult.setStatus("请先结束当前课程的签到！");
            return signinPublishResult;
        }
        //2、设置签到的isEnd为False，表示可以签到。并且创建该签到到数据库
        signinPublish.setIsEnd(false);
        signinPublishService.save(signinPublish);
        //3、修改班课的currentSignin为当前签到
        course.setCurrentSignin(signinPublish.getId());
        courseService.updateCourse(course);
        //4、返回最终结果
        signinPublishResult.setData(signinPublish);
        signinPublishResult.setCode(20000);
        return signinPublishResult;
    }

    @PutMapping("update")
    @ApiOperation(value="结束当前课程的签到")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "courseId", value = "签到所属班课", required = true, dataType = "int")
    })
    public Result stopSign(int courseId){
        Result<Object> result = new Result<>();
        //1、检查当前课程是否存在签到,存在的话修改currentSign为null，并且让那个签到isEnd设置为true
        Course course = courseService.searchCourseById(courseId);
        Integer currentSignin = course.getCurrentSignin();
        if (currentSignin==-1){
            result.setCode(1);
            result.setStatus("当前课程不存在签到！");
            return result;
        }

        SigninPublish signinPublish = signinPublishService.getById(currentSignin);
        signinPublish.setIsEnd(true);
        course.setCurrentSignin(-1);

        courseService.updateById(course);
        signinPublishService.updateById(signinPublish);

        result.setCode(20000);
        return result;
    }
}

