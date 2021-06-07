package com.daoyun.controller;


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

/**
 * <p>
 * 用户表：存储用户信息，同时分类学生老师和管理员（identity） 前端控制器
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-25
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private RedisService redisService;

    @PostMapping("/user")
    @ApiOperation(value="通过手机验证码注册新用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "phone", value = "用户手机号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "password", value = "用户密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "name", value = "用户姓名", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "jobNum", value = "用户学号或工号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "isTeacher", value = "用户是否是老师", required = true, dataType = "Boolean"),
            @ApiImplicitParam(paramType="query", name = "organizationId", value = "用户的组织", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "veriCode", value = "验证码", required = true, dataType = "String")
    })
    public Result register(@RequestParam String phone, @RequestParam String password, @RequestParam String name,
                           @RequestParam String jobNum, @RequestParam Boolean isTeacher,
                           @RequestParam int organizationId, @RequestParam String veriCode){
        User oldUser = userService.selectUserByPhone(phone);
        Result<Object> result = new Result<>();
        // 判断用户是否已经注册
        if(oldUser != null){
            result.setCode(1);
            result.setStatus("该用户已经存在");
            return result;
        }
        String key = "register" + phone;

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

        User newUser = new User();
        newUser.setPhone(phone);
        newUser.setPassword(password);
        newUser.setName(name);
        newUser.setJobNum(jobNum);
        newUser.setIsTeacher(isTeacher);
        newUser.setOrganizationId(organizationId);

        userService.insertUser(newUser);
        return result;
    }

    @GetMapping("/userInfoByPass")
    @ApiOperation(value="通过手机号和密码进行登录并且返回用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "phone", value = "用户手机号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "password", value = "用户密码", required = true, dataType = "String")
    })
    public Result<User> loginByPass(@RequestParam String phone,@RequestParam String password){
        Result<User> result = new Result<>();
        User user = userService.selectUserByPhone(phone);
        if(user == null){
            result.setCode(1);
            result.setStatus("用户不存在");
            return result;
        }

        String userPassword = user.getPassword();

        if(userPassword == password){
            result.setCode(2000);
            result.setData(user);
        }else{
            result.setCode(1);
            result.setStatus("密码不正确");
        }
        return result;
    }

    @GetMapping("/userInfoByVeri")
    @ApiOperation(value="通过手机号和验证码进行登录并且返回用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "phone", value = "用户手机号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "veriCode", value = "验证码", required = true, dataType = "String")
    })
    public Result<User> loginByVeri(@RequestParam String phone,@RequestParam String veriCode){
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
        result.setCode(2000);
        return result;
    }

    @PostMapping("/userAdd")
    @ApiOperation(value="后台管理系统添加用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "phone", value = "用户手机号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "name", value = "用户姓名", required = true, dataType = "String")
    })
    public Result userAddd(@RequestParam String phone,@RequestParam String name){
        User oldUser = userService.selectUserByPhone(phone);
        Result<Object> result = new Result<>();
        // 判断用户是否已经注册
        if(oldUser != null){
            result.setCode(1);
            result.setStatus("该用户已经存在");
            return result;
        }
        User newUser = new User();
        newUser.setPhone(phone);
        newUser.setPassword("12345");
        newUser.setName(name);
        newUser.setIsTeacher(true);
        userService.insertUser(newUser);
        return result;
    }
}

