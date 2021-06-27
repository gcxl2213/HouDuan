package com.daoyun.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 课程信息表：存储课程的名字，上课老师和学校学院学期等
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Course implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private Integer teacherId;

    private String courseNum;

    private String className;

    private String courseName;

    private Integer term;

    private LocalDateTime endTime;

    private boolean isEnd;

    private boolean canJoin;

    private String classroom;

    private String school;

    private Integer currentSignin;

    private String academy;

    private Integer organizationId;

    private Integer creater;

    private LocalDateTime creatTime;

    private Integer modifier;

    private LocalDateTime modifyTime;

}
