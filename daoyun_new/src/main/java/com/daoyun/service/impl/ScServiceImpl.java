package com.daoyun.service.impl;

import com.daoyun.entity.Sc;
import com.daoyun.mapper.ScMapper;
import com.daoyun.service.ScService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生选课表：连接课程表和学生表 服务实现类
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-25
 */
@Service
public class ScServiceImpl extends ServiceImpl<ScMapper, Sc> implements ScService {

}
