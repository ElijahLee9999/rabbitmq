package com.example.rabbitmq.demo.service.impl;

import com.example.rabbitmq.demo.entity.Student;
import com.example.rabbitmq.demo.mapper.StudentMapper;
import com.example.rabbitmq.demo.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 学生表 Service
 *
 * @author Elijah
 * @since 2020-05-29
 */
@Slf4j
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

    @Transactional(rollbackFor = Exception.class)
    public void add(Student student) {
        this.baseMapper.insert(student);
    }

    @Override
    @Cacheable(cacheNames = "Student", key = "'Id-'+#id")
    public Student getById(Long id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = "Student", key = "'Id-'+#student.id")
    })
    public boolean updateById(Student student) {
        return this.baseMapper.updateById(student) > 0;
    }
}
