package com.daoyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.daoyun.entity.Organizatio;
import com.daoyun.mapper.OrganizatioMapper;
import com.daoyun.service.OrganizatioService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.org.apache.xpath.internal.operations.Or;
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

    @Override
    public List<Organizatio> searchOrgTree() {
        QueryWrapper<Organizatio> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", 0);
        // 得到一级节点资源列表
        List<Organizatio> organizatios = organizatioMapper.selectList(queryWrapper);
        if (!organizatios.isEmpty()) {
            for (int i = 0; i < organizatios.size(); i++) {
                setAllChild(organizatios.get(i));
            }
        }
        return organizatios;
    }

    public void setAllChild(Organizatio organizatio) {
        QueryWrapper<Organizatio> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", organizatio.getId());
        // 首次进入这个方法时，查出的是二级节点列表
        // 递归调用时，就能依次查出三、四、五等等级别的节点列表，
        // 递归能实现不同节点级别的无限调用,这个层次不易太深，否则有性能问题
        List<Organizatio> organizatios = organizatioMapper.selectList(queryWrapper);
        organizatio.setChildren(organizatios);
        if (!organizatios.isEmpty()) {
            for (int i = 0; i < organizatios.size(); i++) {
                setAllChild(organizatios.get(i));
            }
        }
    }
}
