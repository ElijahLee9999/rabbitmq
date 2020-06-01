package com.example.rabbitmq.common;

import lombok.Data;

/**
 * @author Elijah
 * @create 2020-05-27 15:14
 */
@Data
public class QueryVo {
    private Long currentPage = 1L;
    private Long pageSize = 10L;
    private Long id;
}
