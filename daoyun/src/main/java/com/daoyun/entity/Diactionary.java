package com.daoyun.entity;

import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 数据字典
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Diactionary implements Serializable {

    private static final long serialVersionUID=1L;

      private Integer id;

    private String detail;

    private String creater;

      @TableField(fill = FieldFill.INSERT)
    private LocalDate createTime;


}
