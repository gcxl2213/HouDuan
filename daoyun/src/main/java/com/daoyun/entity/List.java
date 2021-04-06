package com.daoyun.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单目录表
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class List implements Serializable {

    private static final long serialVersionUID=1L;

      private Integer id;

    private String listName;

    private Integer isVisible;


}
