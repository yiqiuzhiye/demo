package com.demo.xyz.common.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 监控视频状态：0：未上传 ; 1上传监控视频请求已发出 .2：已上传 3：已无效
 *
 * @author alex.xu 2019/08/15
 */
@Getter
@AllArgsConstructor
public enum MonitorFileStatus {
    /**
     * 监控视频未上传
     */
    INIT("0", "未上传"),

    /**
     * 上传监控视频请求已发出
     */
    UPLOAD_REQ_SENT("1", "上传监控视频请求已发出"),

    /**
     * 监控视频已上传
     */
    UPLOAD("2", "监控视频已上传"),

    /**
     * 已无效
     */
    DELETED("9", "已无效");

    /**
     * 值
     */
    private final String value;
    /**
     * 名称
     */
    private final String name;

    // 通过value获取name
    public static String getName(String value) {
        for (MonitorFileStatus status : MonitorFileStatus.values()) {
            if (status.getValue().equals(value)) {
                return status.name;
            }
        }
        return null;
    }

}
