package com.daoyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.daoyun.entity.StuSignin;
import com.daoyun.mapper.StuSigninMapper;
import com.daoyun.service.StuSigninService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    @Resource
    private StuSigninMapper stuSigninMapper;

    @Override
    public List<StuSignin> searchSigninStuid(int studentId, int signinId) {
        QueryWrapper<StuSignin> stuSigninQueryWrapper = new QueryWrapper<>();
        stuSigninQueryWrapper.eq("student_id", studentId)
                .eq("signin_id", signinId);
        List<StuSignin> stuSignins = stuSigninMapper.selectList(stuSigninQueryWrapper);
        return stuSignins;
    }
}
