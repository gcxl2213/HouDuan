package com.daoyun.controller;


import com.daoyun.entity.Course;
import com.daoyun.entity.Result;
import com.daoyun.entity.Sc;
import com.daoyun.service.CourseService;
import com.daoyun.service.ScService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 学生选课表：连接课程表和学生表 前端控制器
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-25
 */
@RestController
@CrossOrigin
@RequestMapping("/sc")
public class ScController {
    @Resource
    private ScService scService;
    @Resource
    private CourseService courseService;

    @PostMapping("create")
    @ApiOperation(value="学生加入班课")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "courseId", value = "要加入的班课的id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "studentId", value = "要加入班课的学生id", required = true, dataType = "int")
    })
    public Result createCourse(Sc sc){
        Result result = new Result();
        result.setCode(1);
        //1、根据courseId查询班课是否存在
        Course course = courseService.searchCourseById(sc.getCourseId());
        if(course == null){
            result.setStatus("该班课不存在");
            return result;
        }
        //2、根据两个id查询是否已经加入班课
        Sc oldSc = scService.searchScByTwoId(sc.getCourseId(), sc.getStudentId());
        if(oldSc != null){
            result.setStatus("你已经加入该班课");
            return result;
        }
        //3、该班课是否允许加入
        if(!course.isCanJoin()){
            result.setStatus("该班课不允许加入");
            return result;
        }
        //4、班课是否结束
        if(course.isEnd()){
            result.setStatus("该班课已经结束");
            return result;
        }
        //以上都没有问题则可以加入该班课
        sc.setExperience(0);
        scService.createSc(sc);
        result.setCode(20000);
        return result;
    }

}

