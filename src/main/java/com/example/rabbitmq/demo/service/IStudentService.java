package com.example.rabbitmq.demo.service;

import com.example.rabbitmq.demo.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 学生表 Service
 *
 * @author Elijah
 * @since 2020-05-29
 */
public interface IStudentService extends IService<Student> {

    /**
     * 根据ID获取实体
     * @param id
     * @return
     */
    Student getById(Long id);

    /**
     * 更新实体
     * @param student
     * @return
     */
    @Override
    boolean updateById(Student student);
}
