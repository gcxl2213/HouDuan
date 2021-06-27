package com.daoyun.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.daoyun.entity.Course;
import com.daoyun.entity.Dict;
import com.daoyun.entity.Result;
import com.daoyun.service.CourseService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
            @ApiImplicitParam(paramType="query", name = "courseName", value = "课程名字", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "className", value = "班级名字", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "term", value = "学期", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "classroom", value = "教室", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "school", value = "学校", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "academy", value = "学院", required = true, dataType = "String")
    })
    public Result<Course> createCourse(Course course){
        Result<Course> result = new Result<>();
        course.setCanJoin(true);
        course.setEnd(false);

        //生成随机班课号--4位字符串
        int num = 1111;
        while(num < 9999){
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
        course.setCourseNum(Integer.toString(num));
        course.setCurrentSignin(-1);
        courseService.insertCourse(course);
        result.setCode(20000);
        result.setData(course);
        return result;
    }

    @GetMapping("search/numObject")
    @ApiOperation(value="根据班课号查询班课")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "courseNum", value = "班课号", required = true, dataType = "String")
    })
    public Result<Course> searchCourseByNumObject(String courseNum){
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

    @GetMapping("search/numList")
    @ApiOperation(value="根据班课号查询班课")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "courseNum", value = "班课号", required = true, dataType = "String")
    })
    public Result<List<Course>> searchCourseByNumList(String courseNum){
        Result<List<Course>> result = new Result<>();
        List<Course> courses = courseService.searchCourseByNum(courseNum);
        if (courses.isEmpty()){
            result.setCode(1);
            result.setStatus("该班课不存在");
        }else{
            result.setData(courses);
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

    @GetMapping("search")
    @ApiOperation(value="查询所有的班课信息，无需任何参数")
    @ApiImplicitParams({
    })
    public Result<List<Course>> searchAllCourse(){
        Result<List<Course>> result = new Result<>();
        result.setData(courseService.searchAllCourse());
        if(result.getData().isEmpty()){
            result.setCode(1);
            result.setStatus("未查询到数据");
        }
        result.setCode(20000);
        return result;
    }

    @DeleteMapping("delete")
    @ApiOperation(value="删除班课")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "根据id进行删除", required = true, dataType = "int")
    })
    public Result deleteCourse(int id){
        Result result = new Result();
        courseService.removeById(id);
        result.setCode(20000);
        return result;
    }

    @GetMapping("search/teacher")
    @ApiOperation(value="根据老师id查询班课")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "teacherId", value = "老师的id", required = true, dataType = "int")
    })
    public Result<List<Course>> searchCourseTeacher(int teacherId){
        Result<List<Course>> result = new Result<>();
        List<Course> courses = courseService.searchCourseTeacher(teacherId);
        if (courses.isEmpty()){
            result.setCode(1);
            result.setStatus("该老师还未创建班课！");
            return result;
        }
        result.setCode(20000);
        result.setData(courses);
        return result;
    }

    @GetMapping("search/student")
    @ApiOperation(value="根据学生ID，查询加入了哪些班课")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "studentId", value = "学生的id", required = true, dataType = "int")
    })
    public Result<List<Course>> searchCourseStudent(int studentId){
        Result<List<Course>> result = new Result<>();
        List<Course> courses = courseService.searchCourseStudent(studentId);
        result.setCode(20000);
        result.setData(courses);
        return result;
    }



}

