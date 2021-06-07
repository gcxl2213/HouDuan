package com.daoyun.controller;


import com.daoyun.entity.Course;
import com.daoyun.entity.Result;
import com.daoyun.service.CourseService;
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
 * 课程信息表：存储课程的名字，上课老师和学校学院学期等 前端控制器
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-25
 */
@RestController
@RequestMapping("/course")
public class CourseController {
    @Resource
    private CourseService courseService;

    @PostMapping("insert")
    @ApiOperation(value="创建新的班课")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "teacher_id", value = "班课老师id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "course_num", value = "班课号码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "course_name", value = "班课名字", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "term", value = "学期", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "classroom", value = "教室", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "school", value = "学校", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "academy", value = "学院", required = true, dataType = "String")
    })
    public Result createCourse(Course courseValue){
        Result result = new Result();

        courseValue.setCanJoin(true);
        courseValue.setEnd(false);

        return result;
    }
}

