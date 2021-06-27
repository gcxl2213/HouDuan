package com.daoyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.daoyun.entity.Dict;
import com.daoyun.mapper.DictMapper;
import com.daoyun.service.DictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-25
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Resource
    private DictMapper dictMapper;

    @Override
    public List<Dict> selectAllDict() {
        QueryWrapper<Dict> dictQueryWrapper = new QueryWrapper<>();
        return dictMapper.selectList(dictQueryWrapper);
    }

    @Override
    public List<Dict> selectDictByName(String name) {
        QueryWrapper<Dict> dictQueryWrapper = new QueryWrapper<>();
        dictQueryWrapper.eq("name", name);
        return dictMapper.selectList(dictQueryWrapper);
    }


}
