package com.daoyun.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
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

      private Integer id;

    private Integer courseId;

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
