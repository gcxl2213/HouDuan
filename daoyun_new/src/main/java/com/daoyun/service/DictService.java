package com.daoyun.service;

import com.daoyun.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-25
 */
public interface DictService extends IService<Dict> {
    /**
     * 根据数据字典项目的名字查询数据字典项
     * @param name
     * @return
     */
    public List<Dict> selectDictByName(String name);

    /**
     * 直接返回所有数据字典项
     * @param
     * @return
     */
    public List<Dict> selectAllDict();



}
