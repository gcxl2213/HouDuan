package com.daoyun.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 数据字典条目表，存储数据字典下的所有选项
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DictionaryItem implements Serializable {

    private static final long serialVersionUID=1L;

      private Integer id;

    private Integer diaId;

    private String value;

    private String describtion;

    /**
     * 若为0，则该条目在数据字典中相当于默认值
     */
    private Integer isDefault;


}
