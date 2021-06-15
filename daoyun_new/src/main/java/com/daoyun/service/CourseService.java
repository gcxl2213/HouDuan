package com.daoyun.service;

import com.daoyun.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.daoyun.entity.Dict;

import java.util.List;

/**
 * <p>
 * 课程信息表：存储课程的名字，上课老师和学校学院学期等 服务类
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-25
 */
public interface CourseService extends IService<Course> {
    /**
     * 给定Course对象，插入数据库
     * 返回boolean类型，表示是否插入成功
     * @param course
     * @return
     */
    public boolean insertCourse(Course course);

    /**
     * 根据courseNum查询班课号，返回对应班课
     * @param courseNum
     * @return
     */
    public List<Course> searchCourseByNum(String courseNum);

    /**
     * 根据id查询班课
     * @param id
     * @return
     */
    public Course searchCourseById(int id);

    /**
     * 根据传进来的Course修改数据库对应项
     * @param course
     * @return
     */
    public boolean updateCourse(Course course);
}
