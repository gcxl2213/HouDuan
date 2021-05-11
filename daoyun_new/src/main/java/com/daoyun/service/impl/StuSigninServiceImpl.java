package com.daoyun.service.impl;

import com.daoyun.entity.StuSignin;
import com.daoyun.mapper.StuSigninMapper;
import com.daoyun.service.StuSigninService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 签到表：存储签到的课程和开始时间以及经纬度 服务实现类
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-25
 */
@Service
public class StuSigninServiceImpl extends ServiceImpl<StuSigninMapper, StuSignin> implements StuSigninService {

}
