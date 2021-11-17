package com.demo.xyz.auth.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.xyz.auth.entity.Staff;
import com.demo.xyz.auth.vo.StaffQueryVo;
import com.demo.xyz.auth.vo.StaffAddReqVo;
import com.demo.xyz.auth.vo.StaffRespVo;

/**
 * 操作员服务类
 *
 * @author Jiahong.Li
 * @since 2021-11-17
 */
public interface IStaffService extends IService<Staff> {

    /**
     * 简单分页查询
     * @param page 分页对象
     * @param vo
     * @return R
     */
    Page listByQuery(Page page, StaffQueryVo vo);

    /**
     * 通过id查询记录
     * @param id
     * @return R
     */
    StaffRespVo findById(Integer id);

    /**
     * 新增或修改记录
     * @param vo
     * @return R
     */
    Boolean addOrUpdate(StaffAddReqVo vo);
}
