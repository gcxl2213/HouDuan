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
 * 
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SigninPublish implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private Integer courseId;

    //1为一键签到，2为限时签到，3为密码签到
    private Integer type;

    private LocalDateTime startTime;

    private Integer savingTime;

    private String password;

    private Float latitude;

    private Float longitude;

    private Boolean isEnd;

    private Integer creater;

    private LocalDateTime creatTime;

    private Integer modifier;

    private LocalDateTime modifyTime;


}
