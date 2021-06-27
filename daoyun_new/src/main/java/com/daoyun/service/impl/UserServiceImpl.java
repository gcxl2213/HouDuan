package com.daoyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.daoyun.entity.Course;
import com.daoyun.entity.Sc;
import com.daoyun.entity.User;
import com.daoyun.mapper.ScMapper;
import com.daoyun.mapper.UserMapper;
import com.daoyun.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 用户表：存储用户信息，同时分类学生老师和管理员（identity） 服务实现类
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private ScMapper scMapper;

    @Override
    public void insertUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public User selectUserByPhone(String phone) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }

    @Override
    public void changeUser(User user) {
        userMapper.updateById(user);
    }

    @Override
    public List<User> searchUserCourse(int courseId) {
        QueryWrapper<Sc> scQueryWrapper = new QueryWrapper<>();
        scQueryWrapper.eq("course_id",courseId);
        List<Sc> scs = scMapper.selectList(scQueryWrapper);
        List<User> users = new LinkedList<>();
        for (int i = 0; i < scs.size(); i++) {
            QueryWrapper<User> courseQueryWrapper = new QueryWrapper<>();
            Integer studentId = scs.get(i).getStudentId();
            courseQueryWrapper.eq("id", studentId);
            User user = userMapper.selectOne(courseQueryWrapper);
            users.add(user);
        }
        return users;
    }


}
