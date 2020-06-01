package com.example.rabbitmq.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 数据表结构字段自动填充
 * @author Elijah
 * @create 2020-05-28 12:40
 */
@Slf4j
@Component
public class CommonMetaObjectHandler implements MetaObjectHandler {

    /** 删除字段 */
    private final static String DELETED = "deleted";

    /** 启用字段 */
    private final static String ENABLED = "enabled";

    /** 创建者字段 */
    private final static String CREATOR = "creator";

    /** 更新者字段 */
    private final static String UPDATER = "updater";

    /** 创建时间 */
    private final static String CREATED_TIME = "createdtime";

    /** 更新时间 */
    private final static String UPDATED_TIME = "updatedtime";

    @Override
    public void insertFill(MetaObject metaObject) {
        DateTime now = DateTime.now();
        String[] setterNames = metaObject.getSetterNames();
        for (String setterName : setterNames) {
            // 删除字段
            if (setterName.toLowerCase().contains(DELETED)) {
                if (StringUtils.isEmpty(metaObject.getValue(setterName))) {
                    this.setFieldValByName(setterName, Boolean.FALSE, metaObject);
                }
            }
            // 启用字段
            if (setterName.toLowerCase().contains(ENABLED)) {
                if (StringUtils.isEmpty(metaObject.getValue(setterName))) {
                    this.setFieldValByName(setterName, Boolean.TRUE, metaObject);
                }
            }
            // 创建者字段
            if (setterName.toLowerCase().contains(CREATOR)) {
                this.setFieldValByName(setterName, this.getUsername(), metaObject);
            }
            // 更新者字段
            if (setterName.toLowerCase().contains(UPDATER)) {
                this.setFieldValByName(setterName, this.getUsername(), metaObject);
            }
            // 创建时间
            if (setterName.toLowerCase().contains(CREATED_TIME)) {
                this.setFieldValByName(setterName, now, metaObject);
            }
            // 更新时间
            if (setterName.toLowerCase().contains(UPDATED_TIME)) {
                this.setFieldValByName(setterName, now, metaObject);
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        DateTime now = DateTime.now();
        String[] setterNames = metaObject.getSetterNames();
        for (String setterName : setterNames) {
            // 更新者字段
            if (setterName.toLowerCase().contains(UPDATER)) {
                this.setFieldValByName(setterName, this.getUsername(), metaObject);
            }

            // 更新时间
            if (setterName.toLowerCase().contains(UPDATED_TIME)) {
                this.setFieldValByName(setterName, now, metaObject);
            }
        }
    }

    /**
     * 获取用户名
     *
     * @return 创建者名称
     */
    private String getUsername() {
        // TODO: 实现获取获取用户名
        return "system";
    }
}
