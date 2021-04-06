package com.daoyun.service.impl;

import com.daoyun.entity.Course;
import com.daoyun.mapper.CourseMapper;
import com.daoyun.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程信息表：存储课程的名字，上课老师和学校学院学期等 服务实现类
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-06
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

}
