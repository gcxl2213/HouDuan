package com.daoyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.daoyun.entity.Organizatio;
import com.daoyun.mapper.OrganizatioMapper;
import com.daoyun.service.OrganizatioService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-25
 */
@Service
public class OrganizatioServiceImpl extends ServiceImpl<OrganizatioMapper, Organizatio> implements OrganizatioService {
    @Resource
    private OrganizatioMapper organizatioMapper;

    @Override
    public List<Organizatio> searchOrgParent(int parentId) {
        QueryWrapper<Organizatio> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",parentId);
        return organizatioMapper.selectList(queryWrapper);
    }

    @Override
    public List<Organizatio> searchOrgId(int id) {
        QueryWrapper<Organizatio> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        return organizatioMapper.selectList(queryWrapper);
    }

    @Override
    public List<Organizatio> searchOrgAll() {
        QueryWrapper<Organizatio> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",0);
        return organizatioMapper.selectList(queryWrapper);
    }
}
