package com.demo.xyz.common.core.vo;

import lombok.Data;

/**
 * @author james
 * @date 2020/12/14 11:27
 * @des
 */
@Data
public class MessageReqVo {
    private CommonInfoVo commonInfoVo;
    private CommonInfoVo commonInfoData;
    private String id;
    private String param;

}
