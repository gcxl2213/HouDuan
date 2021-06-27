package com.daoyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daoyun.entity.DictSub;
import com.daoyun.mapper.DictSubMapper;
import com.daoyun.service.DictSubService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class DictSubServiceImpl extends ServiceImpl<DictSubMapper, DictSub> implements DictSubService{
    @Resource
    private DictSubMapper dictSubMapper;

    @Override
    public List<DictSub> searchDictSubByParentId(int parentId) {
        QueryWrapper<DictSub> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parentId);
        return dictSubMapper.selectList(queryWrapper);
    }

    @Override
    public List<DictSub> searchDictSubByParentDefault(int parentId) {
        QueryWrapper<DictSub> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parentId).eq("default_value", true);
        return dictSubMapper.selectList(queryWrapper);
    }

    @Override
    public List<DictSub> searchDictSubByParentValue(int parentId, int value) {
        QueryWrapper<DictSub> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parentId).eq("value", value);
        return dictSubMapper.selectList(queryWrapper);
    }

    @Override
    public Integer searchDictSubByParentMax(int parentId) {
        QueryWrapper<DictSub> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parentId).orderByDesc("value").last("limit 1");
        DictSub dictSub = dictSubMapper.selectOne(queryWrapper);
        return dictSub.getValue();
    }

}
