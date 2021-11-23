package com.demo.xyz.auth.controller.remote;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.demo.xyz.auth.entity.Staff;
import com.demo.xyz.auth.service.IStaffService;
import com.demo.xyz.auth.vo.LoginVo;
import com.demo.xyz.auth.vo.StaffRespVo;
import com.demo.xyz.common.core.R;
import com.demo.xyz.common.core.ServiceException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/remote")
public class StaffRemoteController {

    private final IStaffService staffService;

    @PostMapping("/login")
    public R<StaffRespVo> login(@RequestBody LoginVo vo){
        Staff staff = staffService.getOne(new LambdaQueryWrapper<Staff>().eq(Staff::getUsername, vo.getUsername()));
        if(staff == null){
            return new R<>();
        }
        StaffRespVo respVo = new StaffRespVo();
        BeanUtils.copyProperties(staff,respVo);
        return new R<>(respVo);
    }

}
