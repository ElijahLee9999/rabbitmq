package com.example.rabbitmq.demo.mapper;

import com.example.rabbitmq.demo.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 学生表 Mapper
 *
 * @author Elijah
 * @since 2020-05-29
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

}
