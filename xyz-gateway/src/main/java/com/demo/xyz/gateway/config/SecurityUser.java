package com.demo.xyz.gateway.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Serializable;
import java.util.Collection;

@Data
public class SecurityUser implements Serializable, UserDetails {
 
    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
 
    private static final long serialVersionUID = 1L;
 
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
    @Getter
    private Integer status;

    /**
     * 租户id
     */
    @Getter
    private Integer tenantId;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.getStatus() == 1?true:false;
    }
}