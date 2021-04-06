package com.daoyun.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权限分配表：关联角色表和权限表
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Allocation implements Serializable {

    private static final long serialVersionUID=1L;

      private Integer id;

    private Integer autId;

    private Integer roleId;


}
