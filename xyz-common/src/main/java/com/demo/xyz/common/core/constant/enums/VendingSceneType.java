package com.demo.xyz.common.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 货柜事件类型，0:用户购物 1:上货 2:下架
 *
 * @author alex.xu
 */
@Getter
@AllArgsConstructor
public enum VendingSceneType {
    /**
     * 货柜事件类型，0:用户购物 1:上货 2:下架
     */
    BUY(0, "开门购物"),
    STOCK_IN(1, "上货"),
    STOCK_OUT(2, "下架"),
    NON_STOCKIN_STOCKOUT(3, "商户用户非上货或非下架任务开门"),
    MANAGE(4,"管理");
    /**
     * 类型值
     */
    private final Integer value;
    /**
     * 类型名称
     */
    private final String name;

    // 通过value获取name
    public static String getName(Integer value) {
        for (VendingSceneType status : VendingSceneType.values()) {
            if (status.getValue().equals(value)) {
                return status.name;
            }
        }
        return null;
    }

    /**
     * 判断是否为购物场景
     *
     * @param value
     * @return
     */
    public static boolean isBuyScene(Integer value) {
        return VendingSceneType.BUY.getValue().equals(value);
    }

    /**
     * 判断是否为非购物场景
     *
     * @param value
     * @return
     */
    public static boolean isNotBuyScene(Integer value) {
        return !VendingSceneType.BUY.getValue().equals(value);
    }
}
