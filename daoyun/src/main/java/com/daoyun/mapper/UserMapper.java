package com.daoyun.mapper;

import com.daoyun.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 用户表：存储用户信息，同时分类学生老师和管理员（identity） Mapper 接口
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-06
 */
public interface UserMapper extends BaseMapper<User> {

}
