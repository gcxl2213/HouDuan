package com.daoyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.daoyun.entity.Dict;
import com.daoyun.mapper.DictMapper;
import com.daoyun.service.DictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-25
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Resource
    private DictMapper dictMapper;

    @Override
    public List<Dict> selectAllDictByPage(Integer currentPage) {
        //分页查询步骤
        QueryWrapper<Dict> dictQueryWrapper = new QueryWrapper<>();
        dictQueryWrapper.groupBy("name");
        IPage<Dict> page = new Page<>(currentPage, 10);
        IPage<Dict> dictIPage = dictMapper.selectPage(page, dictQueryWrapper);
        List<Dict> records = dictIPage.getRecords();
        return records;
    }

    @Override
    public List<Dict> selectDictByName(String name) {
        QueryWrapper<Dict> dictQueryWrapper = new QueryWrapper<>();
        dictQueryWrapper.eq("name", name);
        return dictMapper.selectList(dictQueryWrapper);
    }

    @Override
    public List<Dict> selectAllDict() {
        QueryWrapper<Dict> dictQueryWrapper = new QueryWrapper<>();
        return dictMapper.selectList(dictQueryWrapper);
    }

    @Override
    public boolean insertDict(Dict dict) {
        int insert = dictMapper.insert(dict);
        boolean flag;
        if (insert == 0){
            flag = false;
        }else {
            flag = true;
        }
        return flag;
    }

    @Override
    public void updateDict(Dict dict) {
        dictMapper.updateById(dict);
    }

    @Override
    public void updateDictByName(String oldName,String name,String englishName,int defaultValue) {
        QueryWrapper<Dict> dictQueryWrapper = new QueryWrapper<>();
        dictQueryWrapper.eq("name", oldName);
        List<Dict> dicts = dictMapper.selectList(dictQueryWrapper);
        for (int i = 0; i < dicts.size(); i++) {
            dictMapper.updateById(dicts.get(i).setName(name).setEnglishName(englishName).setDefaultValue(defaultValue));
        }
    }


    @Override
    public void deleteDict(Integer id) {
        dictMapper.deleteById(id);
    }

    @Override
    public void deleteDictByName(String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        dictMapper.deleteByMap(map);
    }
}
