package com.demo.xyz.common.core.constant.enums;

import lombok.Getter;

/**
 * 设备类型，1：掌上超声 2：货柜
 * <p>
 * 目前供设备服务使用
 * </p>
 *
 * @author Sven.Fu
 */
@Getter
public enum DeviceTypeEnum {

    /**
     * 掌上超声设备
     */
    ULTRASOUND(1, "掌上超声", "ultrasound", "com.cloudminds.crss.device.pojo.Ultrasound", null),
    /**
     * 货柜设备
     */
    VENDING(2, "货柜", "vending", "com.cloudminds.crss.device.pojo.Vending", null),
    /**
     * Cloud Ginger
     */
    GINGER(3, "接待机器人", "reception", "com.cloudminds.crss.device.pojo.Ginger", "caos"),
    /**
     * 送货机器人
     */
    DELIVERY(4, "送货机器人", "delivery", "com.cloudminds.crss.device.pojo.Delivery", null),
    /**
     * MissAir
     */
    MISSAIR(5, "MissAir", "missair", "com.cloudminds.crss.device.pojo.MissAir", null),
    /**
     * 清洁机器人
     */
    CLEANX(6, "清洁机器人", "cleanx", "com.cloudminds.crss.device.pojo.CleanX", "cxos"),

    /**
     * CLOUDIA
     */
    CLOUDIA(7, "CLOUDIA", "cloudia", "com.cloudminds.crss.device.pojo.CloudIa", null),
    ;

    private final Integer value;
    private final String name;
    private final String code;
    private final String className;
    private final String operationSystem;

    DeviceTypeEnum(Integer value, String name, String code, String className, String operationSystem) {
        this.value = value;
        this.name = name;
        this.code = code;
        this.className = className;
        this.operationSystem = operationSystem;
    }

    /**
     * 根据value获取对应className
     *
     * @param value
     * @return
     */
    public static String getClassName(Integer value) {
        for (DeviceTypeEnum deviceTypeEnum : values()) {
            if (deviceTypeEnum.getValue() == value) {
                return deviceTypeEnum.getClassName();
            }
        }
        return null;
    }

    /**
     * 根据value获取对应operationSystem
     *
     * @return
     */
    public static String getOperationSystem(Integer value) {
        for (DeviceTypeEnum deviceTypeEnum : values()) {
            if (deviceTypeEnum.getValue() == value) {
                return deviceTypeEnum.getOperationSystem();
            }
        }
        return null;
    }
}
