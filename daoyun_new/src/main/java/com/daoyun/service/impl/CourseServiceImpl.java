package com.daoyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.daoyun.entity.Course;
import com.daoyun.mapper.CourseMapper;
import com.daoyun.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程信息表：存储课程的名字，上课老师和学校学院学期等 服务实现类
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-25
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Resource
    private CourseMapper courseMapper;

    @Override
    public boolean insertCourse(Course course) {
        boolean flag;
        int insert = courseMapper.insert(course);
        if (insert == 0){
            flag = false;
        }else {
            flag = true;
        }
        return flag;
    }

    @Override
    public List<Course> searchCourseByNum(String courseNum) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_num",courseNum);
        return courseMapper.selectList(queryWrapper);
    }

    @Override
    public Course searchCourseById(int id) {
        Course course = courseMapper.selectById(id);
        return course;
    }

    @Override
    public boolean updateCourse(Course course) {
        int i = courseMapper.updateById(course);
        return true;
    }
}
