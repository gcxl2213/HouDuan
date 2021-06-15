package com.daoyun.service;

import com.daoyun.entity.Sc;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 学生选课表：连接课程表和学生表 服务类
 * </p>
 *
 * @author 蔡启铨
 * @since 2021-04-25
 */
public interface ScService extends IService<Sc> {
    /**
     * 根据两个ID查询是否有这个学生选了这门课
     * @param courseId
     * @param studentId
     * @return
     */
    public Sc searchScByTwoId(int courseId, int studentId);

    /**
     * 创建学生选课
     * @param sc
     * @return
     */
    public boolean createSc(Sc sc);

}
