package com.daoyun.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 学生选课表：连接课程表和学生表
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Sc implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 用户Id（学生Id）
     */
      private Integer id;

    private Integer courseId;

    private Integer studentId;


}
