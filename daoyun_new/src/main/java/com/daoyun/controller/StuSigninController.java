package com.daoyun.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.daoyun.entity.*;
import com.daoyun.service.*;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 签到表：存储签到的课程和开始时间以及经纬度 前端控制器
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-25
 */
@RestController
@CrossOrigin
@RequestMapping("/stuSignin")
public class StuSigninController {
    @Resource
    private StuSigninService stuSigninService;
    @Resource
    private SystemenvsService systemenvsService;
    @Resource
    private ScService scService;
    @Resource
    private CourseService courseService;
    @Resource
    private SigninPublishService signinPublishService;

    @PostMapping("create")
    @ApiOperation(value="学生进行签到")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "studentId", value = "签到的学生ID", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "courseId", value = "所属班课ID", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "latitude", value = "签到纬度", required = true, dataType = "float"),
            @ApiImplicitParam(paramType="query", name = "longitude", value = "签到经度", required = true, dataType = "float")
    })
    public Result<Boolean> createStuSignin(StuSignin stuSignin, int courseId){
        Result<Boolean> result = new Result<>();
        Course course = courseService.searchCourseById(courseId);
        if(course==null){
            result.setCode(1);
            result.setData(false);
            result.setStatus("班课不存在");
            return result;
        }
        int signinId = course.getCurrentSignin();
        if(signinId == -1){
            result.setCode(1);
            result.setData(false);
            result.setStatus("当前班课不存在签到");
            return result;
        }
        SigninPublish signinPublish = signinPublishService.getById(signinId);
        if (signinPublish.getIsEnd()){
            result.setCode(1);
            result.setData(false);
            result.setStatus("签到已经结束了啊！");
            return result;
        }
        //1、查询学生是否签到
        List<StuSignin> stuSignins = stuSigninService.searchSigninStuid(stuSignin.getStudentId(), signinId);
        if(!stuSignins.isEmpty()){
            result.setCode(1);
            result.setData(false);
            result.setStatus("学生已经签到了！");
            return result;
        }
        //2、读取系统参数中的经验值，如果没有默认为2
        int exp = 2;
        Systemenvs systemenvs = systemenvsService.searchSysExp();
        if (systemenvs != null){
            exp = systemenvs.getSysValue();
        }
        //3、进行签到后，对应sc经验值增加
        Sc sc = scService.searchScByTwoId(courseId, stuSignin.getStudentId());
        sc.setExperience(sc.getExperience()+exp);
        scService.updateById(sc);
        stuSignin.setSigninId(signinId);
        stuSigninService.save(stuSignin);
        result.setCode(20000);
        result.setData(true);
        return result;
    }
}

