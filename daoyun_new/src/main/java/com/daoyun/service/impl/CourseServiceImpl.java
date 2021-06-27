package com.daoyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.daoyun.entity.Course;
import com.daoyun.entity.Dict;
import com.daoyun.entity.Sc;
import com.daoyun.mapper.CourseMapper;
import com.daoyun.mapper.ScMapper;
import com.daoyun.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
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
    @Resource
    private ScMapper scMapper;

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

    @Override
    public List<Course> searchAllCourse() {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        return courseMapper.selectList(queryWrapper);
    }

    @Override
    public List<Course> searchCourseTeacher(int teacherId) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_id", teacherId);
        return courseMapper.selectList(queryWrapper);
    }

    @Override
    public List<Course> searchCourseStudent(int studentId) {
        QueryWrapper<Sc> scQueryWrapper = new QueryWrapper<>();
        scQueryWrapper.eq("student_id",studentId);
        List<Sc> scs = scMapper.selectList(scQueryWrapper);
        List<Course> courses = new LinkedList<>();
        for (int i = 0; i < scs.size(); i++) {
            QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
            Integer courseId = scs.get(i).getCourseId();
            courseQueryWrapper.eq("id", courseId);
            Course course = courseMapper.selectOne(courseQueryWrapper);
            courses.add(course);
        }
        return courses;
    }
}
