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
 * 用户表：存储用户信息，同时分类学生老师和管理员（identity）
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String phone;

    private String password;

    /**
     * 身份，1为教师，2为学生
     */
    private Integer roleId;

    private String name;

    private String jobNum;

    private Boolean isTeacher;

    private Integer organizationId;

    private Integer creater;

    private LocalDateTime creatTime;

    private Integer modifier;

    private LocalDateTime modifyTime;


}
