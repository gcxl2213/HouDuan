package com.daoyun.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表：存储用户信息，同时分类学生老师和管理员（identity）
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID=1L;

      private Integer id;

    private String loginName;

    private String phone;

    private String password;

    /**
     * 身份，1为教师，2为学生
     */
    private Integer roleId;


}
