package com.daoyun.service;

import com.daoyun.entity.Organizatio;
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
public interface OrganizatioService extends IService<Organizatio> {
    public List<Organizatio> searchOrgParent(int parentId);
    public List<Organizatio> searchOrgId(int id);
    public List<Organizatio> searchOrgAll();
    public List<Organizatio> searchOrgTree();
}
