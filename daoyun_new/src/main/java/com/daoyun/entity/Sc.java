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
 * 学生选课表：连接课程表和学生表
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Sc implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 用户Id（学生Id）
     */
    @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private Integer courseId;

    private Integer studentId;

    private Integer experience;

    private Integer creater;

    private LocalDateTime creatTime;

    private Integer modifier;

    private LocalDateTime modifyTime;


}
