package com.daoyun.controller;



import com.daoyun.entity.Result;
import com.daoyun.service.RedisService;
import com.daoyun.service.UserService;
import com.daoyun.util.MessUtil;
import com.daoyun.util.RandomUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
@Api(value = "code",description = "验证码")
@RestController
@CrossOrigin
@RequestMapping("/verifyCode")
public class VerifyCodeController {
    @Resource
    private RedisService redisService;
    @Resource
    private UserService userService;
    /**
     * 发送手机短信验证码
     * @param phone
     */
    @GetMapping(value = "/mobileCode")
    @ApiOperation(value="发送手机短信验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone",value = "手机号", dataType = "String",required = true),
            @ApiImplicitParam(name = "type",value = "register/login/forget", dataType = "String",required = true)
    })
    public Result getMobileVerify(String phone, String type){

        Result result = new Result();
        if(this.redisService.containsValueKey(type + phone)){
            result.setCode(1);
            result.setStatus("验证码5分钟内有效，请勿重复提交");
            return result;
        }
        String mobileCode = RandomUtils.randomNumberString(4);
        MessUtil.sendMess(phone,mobileCode);
        this.redisService.set(type + phone,mobileCode,30);

        result.setCode(20000);
        result.setStatus("验证码发送成功");
        return result;
    }


    /**
     * 返回一个过期的时间戳
     * @param minute 当前时间+minute分钟后过期
     * @return
     */
    public String getOutDate(int minute){
        long currentTime = System.currentTimeMillis() + minute* 60 * 1000;//一小时有效时间
        Date end = new Date(currentTime);

        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(end);
        return format;
    }
}
