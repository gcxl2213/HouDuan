package com.daoyun.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "返回结果")
public class Result<T> implements Serializable {
    /**
     * 错误代码（2000：无误，1：错误）
     */
    @ApiModelProperty("状态码 2000:成功")
    private Integer code;

    /**
     * 错误信息
     */
    @ApiModelProperty("消息")
    private String status;

    /**
     * 返回数据
     */
    @ApiModelProperty("返回数据")
    private T data;




}
