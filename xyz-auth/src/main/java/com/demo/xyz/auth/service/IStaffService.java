package com.demo.xyz.auth.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.xyz.auth.entity.Staff;
import com.demo.xyz.auth.other.StaffQueryVo;

/**
 * <p>
 * Staff 服务类
 * </p>
 *
 * @author jiahong.li
 * @since 2021-11-12
 */
public interface IStaffService {
    /**
     * Staff简单分页查询
     * @param page
     * @param vo
     * @return
     */
    IPage<Staff> listByQuery(Page page, StaffQueryVo vo);

    /**
     * 通过id查询记录
     * @param id
     * @return
     */
    Staff getById(Integer id);

    /**
     * Staff添加或修改
     * @param staff
     * @return
     */
    Boolean addOrUpdate(Staff staff);

    /**
     * 通过id删除一条记录
     * @param id
     * @return
     */
    Boolean deleteById(Integer id);
}
