package com.demo.xyz.common.core.enums;

/**
 * 活动类型枚举
 */
public enum ActTypeEnum {
    FULL_REDUCTION(0, "满减"),
    FULL_M_FREE_ONE(1, "满M免一"),
    DISCOUNT(2, "折扣"),
    FULL_N_DISCOUNT(3, "满N件折"),
    SPECIAL_OFFER(4, "特价");

    private Integer value;
    private String name;

    ActTypeEnum(Integer value, String name) {
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
