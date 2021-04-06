package com.daoyun.service.impl;

import com.daoyun.entity.Signin;
import com.daoyun.mapper.SigninMapper;
import com.daoyun.service.SigninService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 签到表：存储签到的课程和开始时间以及经纬度 服务实现类
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-06
 */
@Service
public class SigninServiceImpl extends ServiceImpl<SigninMapper, Signin> implements SigninService {

}
