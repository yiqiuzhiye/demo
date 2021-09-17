package com.demo.xyz.common.core.enums;

/**
 * 活动业务类型枚举
 */
public enum ActBizTypeEnum {

    ORDER_ACTIVITY(0, "订单活动"), SINGLE_ORDER_ACTIVITY(1, "单品订单活动");

    private Integer value;
    private String name;


    ActBizTypeEnum(Integer value, String name) {
        this.name = name;
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }
}
