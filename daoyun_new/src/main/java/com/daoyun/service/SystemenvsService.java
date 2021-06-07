package com.daoyun.service;

import com.daoyun.entity.Dict;
import com.daoyun.entity.Systemenvs;
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
public interface SystemenvsService extends IService<Systemenvs> {
    /**
     * 查询系统参数数据库
     * @param currentPage
     * @return
     */
    public List<Systemenvs> selectAllSystemenvsByPage(Integer currentPage);

    /**
     * 给定Dict对象，插入数据库
     * 返回boolean类型，表示是否插入成功
     * @param systemenvs
     * @return
     */
    public boolean insertSystemenvs(Systemenvs systemenvs);

    /**
     * 输入Dict对象，直接根据updateById进行修改
     * @param systemenvs
     * @return
     */
    public void updateSystemenvs(Systemenvs systemenvs);

    /**
     * 输入id，然后删除对应id数据字典项
     * @param id
     * @return
     */
    public void deleteSystemenvs(Integer id);

}
