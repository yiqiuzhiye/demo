package com.demo.xyz.common.core.mongoLog.vo;

import lombok.Data;

import java.util.Date;

@Data
public class HariLogData {
    private String robotCode;
    private int type;
    private String messageType;
    private String req;
    private String resp;
    private int respCode;
    private String level;
    private Date createTime;
}
