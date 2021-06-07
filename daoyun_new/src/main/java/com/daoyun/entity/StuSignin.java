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
 * 签到表：存储签到的课程和开始时间以及经纬度
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StuSignin implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private Integer studentId;

    private Integer signinId;

    private LocalDateTime signinTime;

    /**
     * 经度
     */
    private Float latitude;

    /**
     * 纬度
     */
    private Float longitude;

    private Integer distance;

    private Integer creater;

    private LocalDateTime creatTime;

    private Integer modifier;

    private LocalDateTime modifyTime;


}
