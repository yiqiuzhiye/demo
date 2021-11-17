package com.demo.xyz.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.xyz.auth.entity.Staff;
import com.demo.xyz.auth.mapper.StaffMapper;
import com.demo.xyz.auth.service.IStaffService;
import com.demo.xyz.auth.vo.StaffQueryVo;
import com.demo.xyz.auth.vo.StaffAddReqVo;
import com.demo.xyz.auth.vo.StaffRespVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 操作员服务实现类
 *
 * @author Jiahong.Li
 * @since 2021-11-17
 */
@Slf4j
@Service("staffService")
@AllArgsConstructor
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff> implements IStaffService {
/**
     * 简单分页查询
     * @param page 分页对象
     * @param vo
     * @return R
     */
    @Override
    public Page listByQuery(Page page, StaffQueryVo vo) {
        Page response = this.page(page, createQueryWrapper(vo));
        List<StaffRespVo> respVos = new ArrayList<>();
        response.getRecords().forEach(t -> {
            StaffRespVo respVo = new StaffRespVo();
            BeanUtils.copyProperties(t,respVo);
            respVos.add(respVo);
        });
        response.setRecords(respVos);
        return response;
    }

    /**
     * 通过id查询记录
     * @param id
     * @return R
     */
    @Override
    public StaffRespVo findById(Integer id) {
        Staff staff = this.getById(id);

        StaffRespVo vo = new StaffRespVo();
        BeanUtils.copyProperties(staff,vo);
        return vo;
    }

    /**
     * 新增或修改记录
     * @param vo
     * @return R
     */
    @Override
    public Boolean addOrUpdate(StaffAddReqVo vo) {
        Staff staff = new Staff();
        BeanUtils.copyProperties(vo,staff);
        return this.saveOrUpdate(staff);
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
