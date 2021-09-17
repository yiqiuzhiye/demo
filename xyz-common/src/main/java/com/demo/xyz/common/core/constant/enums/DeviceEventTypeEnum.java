package com.demo.xyz.common.core.constant.enums;

import lombok.Getter;

/**
 * 自定义事件类型
 * <p>
 * 为防止与上报的事件类型冲突，ID从10000开始
 * </p>
 *
 * @author: Sven.Fu
 * @create: 2020-04-09 10:43
 **/
@Getter
public enum DeviceEventTypeEnum {
    /**
     * 设备上线事件
     */
    DEVICE_ONLINE(10000, "online", 3, "设备上线"),
    /**
     * 设备下线事件
     */
    DEVICE_OFFLINE(10001, "offline", 2, "设备下线"),
    /**
     * 任务未执行
     */
    TASK_NOT_EXECUTED(218, "任务未执行", 2, "任务未执行"),

    /**
     * 任务执行中断网
     */
    DEVICE_TASK_OFFLINE(10002, "task executing offline", 2, "任务执行中断网");

    /**
     * 事件类型ID
     */
    private final Integer id;
    /**
     * 事件类型名称
     */
    private final String name;
    /**
     * 事件等级
     */
    private final Integer level;
    /**
     * 事件详情
     */
    private final String details;

    DeviceEventTypeEnum(Integer id, String name, Integer level, String details) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.details = details;
    }
}
