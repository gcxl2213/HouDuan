package com.daoyun.service;

import com.daoyun.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;

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
     * 查询数据字典数据库
     * @param currentPage
     * @return
     */
    public List<Dict> selectAllDictByPage(Integer currentPage);

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

    /**
     * 给定Dict对象，插入数据库
     * 返回boolean类型，表示是否插入成功
     * @param dict
     * @return
     */
    public boolean insertDict(Dict dict);

    /**
     * 输入Dict对象，直接根据updateById进行修改
     * @param dict
     * @return
     */
    public void updateDict(Dict dict);

    public void updateDictByName(String oldName,String name,String englishName,int defaultValue);

    /**
     * 输入id，然后删除对应id数据字典项
     * @param id
     * @return
     */
    public void deleteDict(Integer id);

    public void deleteDictByName(String name);


}
