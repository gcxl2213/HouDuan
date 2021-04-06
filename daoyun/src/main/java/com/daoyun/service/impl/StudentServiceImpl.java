package com.daoyun.service.impl;

import com.daoyun.entity.Student;
import com.daoyun.mapper.StudentMapper;
import com.daoyun.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生表 服务实现类
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-06
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
