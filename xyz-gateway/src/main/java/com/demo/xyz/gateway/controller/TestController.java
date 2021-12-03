package com.demo.xyz.gateway.controller;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/v1")
public class TestController {
    @GetMapping("/test")
    public String test(){
        System.out.println();
        return "访问成功";
    }

}
