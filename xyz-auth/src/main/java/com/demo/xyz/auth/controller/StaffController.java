package com.demo.xyz.auth.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.xyz.auth.service.IStaffService;
import com.demo.xyz.auth.entity.Staff;
import com.demo.xyz.auth.other.StaffQueryVo;
import com.demo.xyz.auth.other.StaffAddReqVo;
import com.demo.xyz.auth.other.StaffRespVo;
import com.demo.xyz.common.core.R;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import lombok.extern.slf4j.Slf4j;
import lombok.AllArgsConstructor;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.ArrayList;

/**
 * <p>
 * Staff 前端控制器
 * </p>
 *
 * @author jiahong.li
 * @since 2021-11-12
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/v1/staff")
public class StaffController {
    private final IStaffService staffService;

    /**
     * 简单分页查询
     * @param page 分页对象
     * @param vo
     * @return R
     */
    @GetMapping("/listByQuery")
    public R<IPage<StaffResqVo>> listByQuery(Page page, StaffQueryVo vo) {
        IPage<Staff> staffPage = staffService.listByQuery(page, vo);
        IPage<StaffResqVo> staffResqPage = new Page<>();
        List<StaffResqVo> staffResqList = new ArrayList<>();
        staffPage.getRecords().forEach(t -> {
            StaffResqVo vo = new StaffResqVo();
            BeanUtils.copyProperties(t,vo);
            staffResqList.add(vo);
        });
        staffResqPage.setRecords(staffResqList);
        return new R<>(staffResqPage);
    }

    /**
     * 通过id查询记录
     * @param id
     * @return R
     */
    @GetMapping("/getById")
    public R<StaffRespVo> getById(@RequestParam("id") Integer id) {
        Staff staff = staffService.getById(id);
        StaffRespVo vo = new StaffRespVo();
        BeanUtils.copyProperties(staff,vo);
        return new R<>(vo);
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
        return new R<>(staffService.addOrUpdate(staff));
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
        return new R<>(staffService.addOrUpdate(staff));
    }

    /**
     * 通过id删除一条记录
     * @param id
     * @return R
     */
    @DeleteMapping("/delete")
    public R deleteById(@RequestParam("id") Integer id) {
        return new R<>(staffService.deleteById(id));
    }
}