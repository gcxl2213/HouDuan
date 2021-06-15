package com.daoyun.controller;


import com.daoyun.entity.Course;
import com.daoyun.entity.Result;
import com.daoyun.service.CourseService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程信息表：存储课程的名字，上课老师和学校学院学期等 前端控制器
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-25
 */
@RestController
@CrossOrigin
@RequestMapping("/course")
public class CourseController {
    @Resource
    private CourseService courseService;

    @PostMapping("create")
    @ApiOperation(value="创建新的班课")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "teacherId", value = "班课老师id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "courseName", value = "班课名字", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "term", value = "学期", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "classroom", value = "教室", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "school", value = "学校", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "academy", value = "学院", required = true, dataType = "String")
    })
    public Result<Course> createCourse(Course course){
        Result<Course> result = new Result<>();
        course.setCanJoin(true);
        course.setEnd(false);
        //生成随机班课号--8位字符串
        int num = 11111111;
        while(num < 99999999){
            String strNum = String.valueOf(num);
            List<Course> courses = courseService.searchCourseByNum(strNum);
            if(courses.isEmpty()){
                course.setCourseNum(strNum);
                break;
            }
            else {
                num++;
            }
        }
        courseService.insertCourse(course);

        result.setCode(20000);
        result.setData(course);
        return result;
    }

    @GetMapping("search/num")
    @ApiOperation(value="根据班课号查询班课")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "courseNum", value = "班课号", required = true, dataType = "String")
    })
    public Result<Course> searchCourseByNum(String courseNum){
        Result<Course> result = new Result<>();
        List<Course> courses = courseService.searchCourseByNum(courseNum);
        if (courses.isEmpty()){
            result.setCode(1);
            result.setStatus("该班课不存在");
        }else{
            Course course = courses.get(0);
            result.setData(course);
            result.setCode(20000);
        }
        return result;
    }

    @PutMapping("update")
    @ApiOperation(value="修改班课信息")
    @ApiImplicitParams({
    })
    public Result<Course> updateCourse(Course course){
        Result<Course> result = new Result<>();
        courseService.updateCourse(course);
        result.setData(course);
        result.setCode(20000);
        return result;
    }
}

