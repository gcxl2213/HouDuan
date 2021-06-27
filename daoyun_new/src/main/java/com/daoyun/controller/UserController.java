package com.daoyun.controller;


import com.daoyun.entity.Course;
import com.daoyun.entity.Result;
import com.daoyun.entity.User;
import com.daoyun.service.RedisService;
import com.daoyun.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 用户表：存储用户信息，同时分类学生老师和管理员（identity） 前端控制器
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-25
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private RedisService redisService;

    @PostMapping("/create/register")
    @ApiOperation(value="通过手机验证码注册新用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "phone", value = "用户手机号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "password", value = "用户密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "name", value = "用户姓名", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "jobNum", value = "用户学号或工号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "isTeacher", value = "用户是否是老师", required = true, dataType = "Boolean"),
            @ApiImplicitParam(paramType="query", name = "organization", value = "用户的组织", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "veriCode", value = "验证码", required = true, dataType = "String")
    })
    public Result register(User user,String veriCode){
        User oldUser = userService.selectUserByPhone(user.getPhone());
        Result<Object> result = new Result<>();
        // 判断用户是否已经注册
        if(oldUser != null){
            result.setCode(1);
            result.setStatus("该用户已经存在");
            return result;
        }
        String key = "register" + user.getPhone();

        // 判断对应验证码是否存在,不存在PASS,存在则继续判断
        if(!this.redisService.containsValueKey(key)){   //不存在，已过期或者还未获取验证码
            result.setCode(1);
            result.setStatus("验证码已失效，请重新获取！");
            return result;
        }

        // 判断验证码是否正确
        if(!veriCode.equals(this.redisService.get(key))){
            result.setCode(1);
            result.setStatus("验证码错误！");
            return result;
        }
        userService.insertUser(user);
        result.setCode(20000);
        return result;
    }

    @GetMapping("/search/password")
    @ApiOperation(value="通过手机号和密码进行登录并且返回用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "phone", value = "用户手机号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "password", value = "用户密码", required = true, dataType = "String")
    })
    public Result<User> loginByPass(User userValue){
        Result<User> result = new Result<>();
        User user = userService.selectUserByPhone(userValue.getPhone());
        if(user == null){
            result.setCode(1);
            result.setStatus("用户不存在");
            return result;
        }

        String userPassword = user.getPassword();

        if(userPassword.equals(userValue.getPassword())){
            result.setCode(20000);
            result.setData(user);
        }else{
            result.setCode(1);
            result.setStatus("密码不正确");
        }
        return result;
    }

    @GetMapping("/search/verifyCode")
    @ApiOperation(value="通过手机号和验证码进行登录并且返回用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "phone", value = "用户手机号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "veriCode", value = "验证码", required = true, dataType = "String")
    })
    public Result<User> loginByVeri(String phone, String veriCode){
        Result<User> result = new Result<>();
        User user = userService.selectUserByPhone(phone);
        if(user == null){
            result.setCode(1);
            result.setStatus("用户不存在");
            return result;
        }
        String key = "login" + phone;

        // 判断对应验证码是否存在,不存在PASS,存在则继续判断
        if(!redisService.containsValueKey(key)){
            result.setCode(1);
            result.setStatus("验证码已失效，请重新获取！");
            return result;
        }

        // 判断验证码是否正确
        if(!veriCode.equals(redisService.get(key))){
            result.setCode(1);
            result.setStatus("验证码错误！");
            return result;
        }
        result.setData(user);
        result.setCode(20000);
        return result;
    }

    @PostMapping("/create/add")
    @ApiOperation(value="后台管理系统添加用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "phone", value = "用户手机号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "name", value = "用户姓名", required = true, dataType = "String")
    })
    public Result userAddd(User userValue){
        User oldUser = userService.selectUserByPhone(userValue.getPhone());
        Result<Object> result = new Result<>();
        // 判断用户是否已经注册
        if(oldUser != null){
            result.setCode(1);
            result.setStatus("该用户已经存在");
            return result;
        }
        User newUser = new User();
        newUser.setPhone(userValue.getPhone());
        newUser.setPassword("12345");
        newUser.setName(userValue.getName());
        newUser.setIsTeacher(true);
        userService.insertUser(newUser);
        result.setCode(20000);
        return result;
    }

    @PutMapping("/update/password")
    @ApiOperation(value="用户忘记密码，通过手机验证码修改")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "phone", value = "用户手机号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "password", value = "新密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "veriCode", value = "验证码", required = true, dataType = "String")
    })
    public Result forgetPassword(User userValue, String veriCode){
        Result<Object> result = new Result<>();
        User oldUser = userService.selectUserByPhone(userValue.getPhone());
        // 判断用户是否已经注册
        if(oldUser == null){
            result.setCode(1);
            result.setStatus("该用户不存在");
            return result;
        }
        // 判断对应验证码是否存在,不存在PASS,存在则继续判断
        String key = "forget" + userValue.getPhone();
        if(!this.redisService.containsValueKey(key)){   //不存在，已过期或者还未获取验证码
            result.setCode(1);
            result.setStatus("验证码不存在！");
            return result;
        }
        // 判断验证码是否正确
        if(!veriCode.equals(this.redisService.get(key))){
            result.setCode(1);
            result.setStatus("验证码错误！");
            return result;
        }
        oldUser.setPassword(userValue.getPassword());
        userService.changeUser(oldUser);
        result.setCode(20000);
        return result;
    }

    @GetMapping("search/course")
    @ApiOperation(value="根据班课id，查询有哪些学生")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "courseId", value = "班课的id", required = true, dataType = "int")
    })
    public Result<List<User>> searchUserCourse(int courseId){
        Result<List<User>> result = new Result<>();
        List<User> users = userService.searchUserCourse(courseId);
        result.setCode(20000);
        result.setData(users);
        return result;
    }

    @PutMapping("update")
    @ApiOperation(value="修改用户信息")
    @ApiImplicitParams({
    })
    public Result<User> updateUser(User user){
        Result<User> result = new Result<>();
        userService.updateById(user);
        result.setCode(20000);
        result.setData(user);
        return result;
    }
}

