package com.daoyun.service.impl;

import com.daoyun.entity.List;
import com.daoyun.mapper.ListMapper;
import com.daoyun.service.ListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单目录表 服务实现类
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-06
 */
@Service
public class ListServiceImpl extends ServiceImpl<ListMapper, List> implements ListService {

}
