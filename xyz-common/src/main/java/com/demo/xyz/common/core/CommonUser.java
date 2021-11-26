package com.demo.xyz.common.core;

import cn.hutool.json.JSONObject;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Data
public class CommonUser implements Serializable {

//    private static final long serialVersionUID = -2485962623814068748L;
//    /**
//     * 主键
//     */
//    private Integer id;
//
//    /**
//     * 租户code
//     */
//    private String tenantId;
//
//    /**
//     * 姓名
//     */
//    private String name;
//
//    /**
//     * 用户名
//     */
//    private String username;
//
//    /**
//     * 密码
//     */
//    private String password;
//
//    /**
//     * 手机号
//     */
//    private String phone;
//
//    /**
//     * 邮箱
//     */
//    private String email;
//
//    /**
//     * 1有效 9删除
//     */
//    private Integer status;
//
//
//    private List<JSONObject> authorities;

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 邮箱号码
     */
    @Getter
    private String email;

    /**
     * 手机号
     */
    @Getter
    private String phone;

    /**
     * 用户名
     */
    @Getter
    private String username;

    /**
     * 登录密码
     */
    @Getter
    private String password;

    /**
     * 状态 1：有效9：删除
     */
    //@Getter
    //private Integer status = 1;

    /**
     * 租户id
     */
    @Getter
    private Integer tenantId;

    private List<JSONObject> authorities;
}
