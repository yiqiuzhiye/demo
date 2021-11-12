package com.demo.xyz.auth.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.xyz.auth.mapper.StaffMapper;
import com.demo.xyz.auth.service.IStaffService;
import com.demo.xyz.auth.entity.Staff;
import com.demo.xyz.auth.other.StaffQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import lombok.AllArgsConstructor;

import static com.baomidou.mybatisplus.extension.toolkit.SqlHelper.retBool;

/**
 * <p>
 * Staff 服务实现类
 * </p>
 *
 * @author jiahong.li
 * @since 2021-11-12
 */
@Slf4j
@Service("staffService")
@AllArgsConstructor
public class StaffServiceImpl implements IStaffService {

    private final StaffMapper staffMapper;

    /**
     * 简单分页查询
     * @param page
     * @param vo
     * @return
     */
    @Override
    public IPage<Staff> listByQuery(Page page, StaffQueryVo vo) {
        return staffMapper.selectPage(page, doQueryVo(vo));
    }

    /**
     * 通过id查询记录
     * @param id
     * @return
     */
    @Override
    public Staff getById(Integer id){
        return staffMapper.selectById(id);
    }

    /**
     * Staff添加或修改
     * @param staff
     * @return
     */
    @Override
    public Boolean addOrUpdate(Staff staff){
        if(staff.getId() == null){
            return retBool(staffMapper.insert(staff));
        }else{
            return retBool(staffMapper.updateById(staff));
        }
    }

    /**
     * 通过id删除一条记录
     * @param id
     * @return
     */
    @Override
    public Boolean deleteById(Integer id){
        return retBool(staffMapper.deleteById(id));
    }

    /**
    * 处理查询参数
    * @param vo
    * @return
    */
    public LambdaQueryWrapper doQueryVo(StaffQueryVo vo){
        LambdaQueryWrapper queryWrapper = new LambdaQueryWrapper();
        return queryWrapper;
    }

}
