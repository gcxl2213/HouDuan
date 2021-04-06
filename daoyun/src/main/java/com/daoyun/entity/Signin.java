package com.daoyun.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 签到表：存储签到的课程和开始时间以及经纬度
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Signin implements Serializable {

    private static final long serialVersionUID=1L;

      private Integer id;

    private Integer courseId;

    private String password;

    private LocalDate startTime;

    /**
     * 经度
     */
    private BigDecimal latitude;

    /**
     * 纬度
     */
    private BigDecimal longitude;


}
