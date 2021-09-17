package com.demo.xyz.common.core.vo;

import lombok.Data;

/**
 * @author james
 * @date 2020/12/14 10:41
 * @des
 */
@Data
public class MessageDefinitionVo {
    private String messageId;
    private int type;
    private int needResponse;
    private String url;
    private String routingKey;
    private int expirationTime;


}
