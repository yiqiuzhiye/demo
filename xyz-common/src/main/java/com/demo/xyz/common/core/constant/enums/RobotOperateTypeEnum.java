package com.demo.xyz.common.core.constant.enums;

import lombok.Getter;

/**
 * 机器人操作类型（下发控制指令使用）
 *
 * @author: Sven.Fu
 * @create: 2020-08-28 18:05
 **/
@Getter
public enum RobotOperateTypeEnum {
    ROBOT_REBOOT("robotreboot", "重启"),

    ROBOT_SHUTDOWN("robotshutdown", "关机"),

    ROBOT_CALIBRATE("robotcalibrate", "地图校准"),

    ROBOT_EMERGENCY_STOP("robotemergencystop", "紧急制动"),

    ROBOT_EMERGENCY_RESUME("robotemergencyresume", "制动恢复"),
    /**
     * 查询指令类型：全部
     */
    QUERY_TYPE_ALL("0", "全部地图/任务"),
    /**
     * 查询指令类型：当前
     */
    QUERY_TYPE_CURRENT("1", "当前地图/任务"),
    /**
     * 查询指令类型：指定
     */
    QUERY_TYPE_SINGLE("2", "指定地图/任务"),
    ;

    private String value;
    private String desc;

    RobotOperateTypeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
