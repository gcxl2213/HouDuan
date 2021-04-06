package com.daoyun.service.impl;

import com.daoyun.entity.Authority;
import com.daoyun.mapper.AuthorityMapper;
import com.daoyun.service.AuthorityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表：数字编号分别对应其中一种后台操作权限 服务实现类
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-06
 */
@Service
public class AuthorityServiceImpl extends ServiceImpl<AuthorityMapper, Authority> implements AuthorityService {

}
