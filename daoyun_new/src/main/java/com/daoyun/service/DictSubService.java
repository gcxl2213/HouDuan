package com.daoyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.daoyun.entity.DictSub;
import java.util.List;

public interface DictSubService extends IService<DictSub> {
    public List<DictSub> searchDictSubByParentId(int parentId);
    public List<DictSub> searchDictSubByParentDefault(int parentId);
    public List<DictSub> searchDictSubByParentValue(int parentId, int value);
    public Integer searchDictSubByParentMax(int parentId);

}
