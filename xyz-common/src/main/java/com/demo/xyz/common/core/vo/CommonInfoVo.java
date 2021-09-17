package com.demo.xyz.common.core.vo;

import lombok.Data;

/**
 * @author james
 * @date 2020/12/14 11:27
 * @des
 */
@Data
public class CommonInfoVo {
    private String tenantCode;
    private String rocUserCode;
    private String robotCode;
    private String robotType;
    private String serviceCode;
    private String rcuCode;
    private String guid;
    private long timestamp;
    private Integer deviceType;

}
