package com.daoyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.daoyun.entity.Sc;
import com.daoyun.mapper.ScMapper;
import com.daoyun.service.ScService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 学生选课表：连接课程表和学生表 服务实现类
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-25
 */
@Service
public class ScServiceImpl extends ServiceImpl<ScMapper, Sc> implements ScService {

    @Resource
    private ScMapper scMapper;

    @Override
    public Sc searchScByTwoId(int courseId, int studentId) {
        QueryWrapper<Sc> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId).eq("student_id", studentId);
        Sc sc = scMapper.selectOne(queryWrapper);
        return sc;
    }

    @Override
    public boolean createSc(Sc sc) {
        int insert = scMapper.insert(sc);
        return true;
    }

    @Override
    public void deleteSc(int courseId, int studentId) {
        QueryWrapper<Sc> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId).eq("student_id",studentId);
        scMapper.delete(queryWrapper);
    }
}
