package com.demo.xyz.client.service;


import com.demo.xyz.client.dto.auth.LoginDto;
import com.demo.xyz.client.dto.auth.StaffDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "xyz-auth")
public interface AuthRemoteService {
    @PostMapping("/remote/login")
    StaffDto login(@RequestBody LoginDto dto);
}
