package com.daoyun.service.impl;

import com.daoyun.entity.Allocation;
import com.daoyun.mapper.AllocationMapper;
import com.daoyun.service.AllocationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限分配表：关联角色表和权限表 服务实现类
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-06
 */
@Service
public class AllocationServiceImpl extends ServiceImpl<AllocationMapper, Allocation> implements AllocationService {

}
