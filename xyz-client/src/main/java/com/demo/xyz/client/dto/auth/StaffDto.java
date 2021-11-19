package com.demo.xyz.client.dto.auth;

import lombok.Data;

import java.io.Serializable;

@Data
public class StaffDto implements Serializable {
    private Integer id;

    private String username;

    private String phone;

    private String password;
}
