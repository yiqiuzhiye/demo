package com.demo.xyz.auth.vo;

import java.io.Serializable;
import lombok.Data;

/**
 * 新增/修改操作员
 *
 * @author Jiahong.Li
 * @since 2021-11-17
 */
@Data
public class StaffAddReqVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;
    /**
     * 租户code
     */
    private String tenantCode;
    /**
     * 姓名
     */
    private String name;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
}
