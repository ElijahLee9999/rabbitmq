package com.example.rabbitmq.config;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.example.rabbitmq.utils.IdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * MyBatis-Plus主键生成规则
 * @author Elijah
 * @create 2020-05-28 15:29
 */
@Slf4j
@Component
public class IdWorkerIdGenerator implements IdentifierGenerator {
    @Override
    public Number nextId(Object entity) {
        return IdGenerator.getId();
    }
}
