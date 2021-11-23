package com.demo.xyz.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.xyz.auth.entity.Staff;
import com.demo.xyz.auth.mapper.StaffMapper;
import com.demo.xyz.auth.service.IStaffService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 操作员服务实现类
 *
 * @author Jiahong.Li
 * @since 2021-11-23
 */
@Slf4j
@Service("staffService")
@AllArgsConstructor
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff> implements IStaffService {

}
