package com.daoyun.service;

import com.daoyun.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户表：存储用户信息，同时分类学生老师和管理员（identity） 服务类
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-25
 */
public interface UserService extends IService<User> {
    /**
     * 创建一个新的用户,直接传一个用户给该服务，会插入数据库中
     * @param user
     * @return
     */
    public void insertUser(User user);

    /**
     * 根绝手机号查询用户是否存在,存在的话返回该用户，不存在返回null
     * @param phone
     * @return
     */
    public User selectUserByPhone(String phone);

    /**
     * 根据id修改
     * @param user
     */
    public void changeUser(User user);

    public List<User> searchUserCourse(int courseId);
}
