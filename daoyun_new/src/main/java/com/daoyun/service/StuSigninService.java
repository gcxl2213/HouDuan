package com.daoyun.service;

import com.daoyun.entity.StuSignin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.daoyun.entity.Systemenvs;

import java.util.List;

/**
 * <p>
 * 签到表：存储签到的课程和开始时间以及经纬度 服务类
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-25
 */
public interface StuSigninService extends IService<StuSignin> {
    public List<StuSignin> searchSigninStuid(int studentId, int signinId);

}
