package com.daoyun.service.impl;

import com.daoyun.entity.Role;
import com.daoyun.mapper.RoleMapper;
import com.daoyun.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  角色表：区分管理员，学生，教师 服务实现类
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-25
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
