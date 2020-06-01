package com.example.rabbitmq.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Elijah
 * @create 2020-05-27 14:32
 */
@Data
@AllArgsConstructor
public class PageInfo<T> {
    private long currentPage;
    private long totalPage;
    private long pageSize;
    private long total;
    private List<T> item;

    public PageInfo() {
        this.item = new ArrayList<>();
    }

    public PageInfo(IPage<T> page) {
        this.currentPage = page.getCurrent();
        this.totalPage = page.getPages();
        this.pageSize = page.getSize();
        this.total = page.getTotal();
        List<T> item = page.getRecords();
        if (null == item || item.size() == 0) {
            this.item = new ArrayList<>();
        } else {
            this.item = item;
        }
    }
}
