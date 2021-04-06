package com.daoyun.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权限表：数字编号分别对应其中一种后台操作权限
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Authority implements Serializable {

    private static final long serialVersionUID=1L;

      private Integer id;

    private String autauthorityName;

    private String autauthorityDescribe;

    private Integer listId;


}
