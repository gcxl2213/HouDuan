package com.daoyun.service.impl;

import com.daoyun.entity.User;
import com.daoyun.mapper.UserMapper;
import com.daoyun.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表：存储用户信息，同时分类学生老师和管理员（identity） 服务实现类
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
