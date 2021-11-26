package com.demo.xyz.auth.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.xyz.auth.entity.Staff;
import com.demo.xyz.auth.service.IStaffService;
import com.demo.xyz.auth.vo.StaffAddReqVo;
import com.demo.xyz.auth.vo.StaffQueryVo;
import com.demo.xyz.auth.vo.StaffRespVo;
import com.demo.xyz.common.constant.RedisKey;
import com.demo.xyz.common.core.CommonUser;
import com.demo.xyz.common.core.CommonUserContext;
import com.demo.xyz.common.core.R;
import com.demo.xyz.common.core.ServiceException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 操作员controller
 *
 * @author Jiahong.Li
 * @since 2021-11-23
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/v1/staff")
public class StaffController {
    private final IStaffService staffService;
    private final RedisTemplate redisTemplate;

    /**
     * 简单分页查询
     * @param page 分页对象
     * @param vo
     * @return R
     */
    @GetMapping("/listByQuery")
    public R<Page<StaffRespVo>> listByQuery(Page page, StaffQueryVo vo) {
        CommonUser user = CommonUserContext.getUser();
        Page response = staffService.page(page, createQueryWrapper(vo));
        List<StaffRespVo> respVos = new ArrayList<>();
        response.getRecords().forEach(t -> {
            StaffRespVo respVo = new StaffRespVo();
            BeanUtils.copyProperties(t,respVo);
            respVos.add(respVo);
        });
        response.setRecords(respVos);
        return new R<>(response);
    }

    /**
     * 通过id查询记录
     * @param id
     * @return R
     */
    @GetMapping("/getById")
    public R<StaffRespVo> getById(@RequestParam("id") Integer id) {
        Staff staff = staffService.getById(id);
        StaffRespVo respVo = new StaffRespVo();
        if(staff != null){
            BeanUtils.copyProperties(staff,respVo);
        }
        return new R<>(respVo);
    }

    /**
     * 新增记录
     * @param vo
     * @return R
     */
    @PostMapping("/add")
    public R add(@RequestBody StaffAddReqVo vo) {
        Staff staff = new Staff();
        BeanUtils.copyProperties(vo,staff);
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        staff.setPassword(encoder.encode(staff.getPassword()));
        if(staffService.saveOrUpdate(staff)){
            CommonUser commonUser = new CommonUser();
            BeanUtils.copyProperties(staff,commonUser);
            redisTemplate.opsForValue().set(RedisKey.LOGIN_USER_CACHE_KEY + staff.getUsername(),commonUser);
        }
        return new R<>();
    }

    /**
     * 修改记录
     * @param vo
     * @return R
     */
    @PutMapping("/updateById")
    public R updateById(@RequestBody StaffAddReqVo vo) {
        Staff staff = new Staff();
        BeanUtils.copyProperties(vo,staff);
        if(StringUtils.isNotBlank(vo.getPassword())){
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            staff.setPassword(encoder.encode(staff.getPassword()));
        }
        if (staffService.saveOrUpdate(staff)){
            CommonUser commonUser = new CommonUser();
            BeanUtils.copyProperties(staff,commonUser);
            redisTemplate.opsForValue().set(RedisKey.LOGIN_USER_CACHE_KEY + staff.getUsername(),commonUser);
        }
        return new R<>();
    }

    /**
     * 通过id删除一条记录
     * @param id
     * @return R
     */
    @DeleteMapping("/deleteById")
    public R deleteById(@RequestParam("id") Integer id) {
        return new R<>(staffService.removeById(id));
    }

    /**
     * 处理查询参数
     * @param vo
     * @return
     */
    public LambdaQueryWrapper createQueryWrapper(StaffQueryVo vo){
        LambdaQueryWrapper<Staff> queryWrapper = new LambdaQueryWrapper();
        if(vo.getId() != null){
            queryWrapper.eq(Staff::getId,vo.getId());
        }
        return queryWrapper;
    }
}