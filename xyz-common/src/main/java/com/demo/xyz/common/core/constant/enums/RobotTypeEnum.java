package com.demo.xyz.common.core.constant.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * ROC机器人类型
 * <p>
 * deviceType 对应 DeviceTypeEnum 里的枚举值，未定义的为0
 * </p>
 *
 * @link com.cloudminds.crss.common.core.constant.enums.DeviceTypeEnum
 * @author: Sven.Fu
 * @create: 2020-01-09 15:31
 **/
@Getter
public enum RobotTypeEnum {
    /**
     * Cloud Pepper
     */
    // CLOUD_PEPPER(1, "Cloud Pepper", "pepper", 5),
    CLOUD_PEPPER(1, "Cloud Pepper", "pepper", 3),
    /**
     * Cloud META
     */
    CLOUD_META(2, "Cloud META", "meta", 0),
    /**
     * Cloud Patrol
     */
    CLOUD_PATROL(3, "Cloud Patrol", "patrol", 0),
    /**
     * Cloud Ginger
     */
    // CLOUD_GINGER(4, "Cloud Ginger", "ginger", 5),
    CLOUD_GINGER(4, "Cloud Ginger", "ginger", 3),
    /**
     * Cloud NUC
     */
    CLOUD_NUC(5, "Cloud NUC", "nuc", 0),
    /**
     * Cloud PAD
     */
    CLOUD_PAD(6, "Cloud PAD", "pad", 0),
    /**
     * Cloud Porter
     */
    CLOUD_PORTER(7, "Cloud Porter", "porter", 0),
    /**
     * Cloud iPal
     */
    CLOUD_IPAL(8, "Cloud iPal", "ipal", 0),
    /**
     * Cloud Hamitao
     */
    CLOUD_HAMITAO(9, "Cloud Hamitao", "hamitao", 0),
    /**
     * Cloud Danny
     */
    CLOUD_DANNY(10, "Cloud Danny", "danny", 0),
    /**
     * Cloud Raman
     */
    CLOUD_RAMAN(11, "Cloud Raman", "raman", 0),
    /**
     * Virtual Robot
     */
    // VIRTUAL_ROBOT(12, "Virtual Robot", "virtual", 0),
    /**
     * Cloudia
     */
    CLOUDIA(12, "Cloudia", "cloudia", 7),
    /**
     * Cloud Ginseng
     */
    // CLOUD_GINSENG(13, "Cloud Ginseng", "ginseng", 0),
    /**
     * Cloud Seeds
     */
    CLOUD_SEEDS(13, "Cloud Seeds", "seeds", 3),
    /**
     * Cloud CleanX-Ccle-ic-2
     */
    CLOUD_CLEANX(14, "Cloud CleanX-Ccle-ic-2", "cleanx-ccle-ic-2", 6),
    /**
     * Cloud Vending
     */
    CLOUD_VENDING(15, "Cloud Vending", "vending", 2),
    /**
     * Cloud CCLE-PR-2-60
     */
    // CLOUD_CCLE(16, "Cloud CCLE-PR-2-60", "ccle-pr-2-60", 6),
    /**
     * Cloud Cleaning
     */
    CLOUD_CLEANING(16, "Cloud Cleaning", "cleaning", 6),
    /**
     * Cloud Ginger-Air
     */
    // CLOUD_GINGER_AIR(17, "Cloud Ginger-Air", "ginger-air", 5),
    CLOUD_GINGER_AIR(17, "Cloud Ginger-Air", "ginger-air", 3),
    /**
     * Cloud Delivery
     */
    CLOUD_DELIVERY(18, "Cloud Delivery", "delivery", 4),
    /**
     * Cloud Usound
     */
    CLOUD_USOUND(19, "Cloud Usound", "usound", 1),
    /**
     * Cloud Beetle
     */
    CLOUD_BEETLE(20, "Cloud Beetle", "beetle", 0),
    /**
     * Funation Player
     */
    FUNATION_PLAYER(21, "Funation Player", "player", 0),
    /**
     * Cloud Ginger-Lite
     */
    CLOUD_GINGER_LITE(22, "Cloud Ginger-Lite", "ginger-lite", 3),
    /**
     * Cloud ETM
     */
    CLOUD_ETM(23, "Cloud ETM", "etm", 0),
    ;

    private final Integer value;
    private final String name;
    private final String code;
    private final Integer deviceType;

    RobotTypeEnum(Integer value, String name, String code, Integer deviceType) {
        this.value = value;
        this.name = name;
        this.code = code;
        this.deviceType = deviceType;
    }

    /**
     * 根据机器人类型获取设备类型，未定义的为0
     *
     * @param value
     * @return
     */
    public static Integer getDeviceType(Integer value) {
        for (RobotTypeEnum robotTypeEnum : RobotTypeEnum.values()) {
            if (robotTypeEnum.getValue().equals(value)) {
                return robotTypeEnum.getDeviceType();
            }
        }
        return 0;
    }

    /**
     * 根据机器人类型获取机器人类型编码
     *
     * @param value
     * @return
     */
    public static String getRobotTypeCode(Integer value) {
        for (RobotTypeEnum robotTypeEnum : RobotTypeEnum.values()) {
            if (robotTypeEnum.getValue().equals(value)) {
                return robotTypeEnum.getCode();
            }
        }
        return null;
    }

    /**
     * 获取 MissAir 机器人类型列表（临时使用，全面接入接待机器人portal后删除）
     *
     * @return
     */
    public static List<String> getMissAirRobotTypeList() {
        return Arrays.asList("pepper", "ginger", "ginger-air");
    }

    /**
     * 根据robotType判断是否为MissAir机器人
     *
     * @param robotType
     * @return
     */
    public static boolean isMissAirRobot(String robotType) {
        return getMissAirRobotTypeList().contains(robotType);
    }

}
