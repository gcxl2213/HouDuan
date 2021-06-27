package com.daoyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.daoyun.entity.Dict;
import com.daoyun.entity.Systemenvs;
import com.daoyun.mapper.DictMapper;
import com.daoyun.mapper.SystemenvsMapper;
import com.daoyun.service.SystemenvsService;
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
public class SystemenvsServiceImpl extends ServiceImpl<SystemenvsMapper, Systemenvs> implements SystemenvsService {
    @Resource
    private SystemenvsMapper systemenvsMapper;

    @Override
    public List<Systemenvs> selectAllSystemenvsByPage(Integer currentPage) {
        QueryWrapper<Systemenvs> systemenvsQueryWrapper = new QueryWrapper<>();
        IPage<Systemenvs> page = new Page<>(currentPage, 10);
        IPage<Systemenvs> systemenvsIPage = systemenvsMapper.selectPage(page, systemenvsQueryWrapper);
        List<Systemenvs> records = systemenvsIPage.getRecords();
        return records;
    }

    @Override
    public boolean insertSystemenvs(Systemenvs systemenvs) {
        int insert = systemenvsMapper.insert(systemenvs);
        boolean flag;
        if (insert == 0){
            flag = false;
        }else {
            flag = true;
        }
        return flag;
    }

    @Override
    public void updateSystemenvs(Systemenvs systemenvs) {
        systemenvsMapper.updateById(systemenvs);
    }

    @Override
    public void deleteSystemenvs(Integer id) {
        systemenvsMapper.deleteById(id);
    }

    @Override
    public boolean keyIsExist(String key) {
        QueryWrapper<Systemenvs> systemenvsQueryWrapper = new QueryWrapper<>();
        systemenvsQueryWrapper.eq("sys_key",key);
        List<Systemenvs> systemenvsList = systemenvsMapper.selectList(systemenvsQueryWrapper);
        boolean flag;
        if(systemenvsList.isEmpty()){
            flag = false;
        }else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Systemenvs searchSysExp() {
        QueryWrapper<Systemenvs> systemenvsQueryWrapper = new QueryWrapper<>();
        systemenvsQueryWrapper.eq("sys_key","exp");
        Systemenvs systemenvs = systemenvsMapper.selectOne(systemenvsQueryWrapper);
        return systemenvs;
    }

}
