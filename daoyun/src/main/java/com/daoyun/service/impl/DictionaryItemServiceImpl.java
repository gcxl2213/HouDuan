package com.daoyun.service.impl;

import com.daoyun.entity.DictionaryItem;
import com.daoyun.mapper.DictionaryItemMapper;
import com.daoyun.service.DictionaryItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数据字典条目表，存储数据字典下的所有选项 服务实现类
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-06
 */
@Service
public class DictionaryItemServiceImpl extends ServiceImpl<DictionaryItemMapper, DictionaryItem> implements DictionaryItemService {

}
