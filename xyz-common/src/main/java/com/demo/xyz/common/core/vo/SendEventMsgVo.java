package com.demo.xyz.common.core.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Ethan.Chen QQ:913242898
 * @date 2021/5/6
 * @description 发送事件消息内容
 */
@Data
public class SendEventMsgVo implements Serializable, Cloneable {

    private static final long serialVersionUID = -4525216400364869057L;

    /**
     * 消息来源，默认：CROSS
     */
    private String source = "CROSS";

    /**
     * 消息类型Id
     */
    private Integer msgTypeId;

    private String robotCode;

    private String tenantCode;

    /**
     * 消息时间(ms)
     */
    private Long ts;

    /**
     * 消息详细说明
     */
    private String details;

}
