package com.daoyun.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 课程信息表：存储课程的名字，上课老师和学校学院学期等
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Course implements Serializable {

    private static final long serialVersionUID=1L;

      private Integer id;

    private String courseName;

    private Integer teacherId;

    /**
     * 学期：2020-2021-1（2020-2021-2）
     */
    private String term;

    /**
     * 学校
     */
    private String school;

    private String classroom;

    private String classTime;

    private String classWeek;


}
