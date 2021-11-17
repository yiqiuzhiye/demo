package com.demo.xyz.auth.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.xyz.auth.service.IStaffService;
import com.demo.xyz.auth.vo.StaffQueryVo;
import com.demo.xyz.auth.vo.StaffAddReqVo;
import com.demo.xyz.auth.vo.StaffRespVo;
import com.demo.xyz.common.core.R;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 操作员controller
 *
 * @author Jiahong.Li
 * @since 2021-11-17
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
    public R<Page<StaffRespVo>> listByQuery(Page page, StaffQueryVo vo) {
        return new R<>(staffService.listByQuery(page, vo));
    }

    /**
     * 通过id查询记录
     * @param id
     * @return R
     */
    @GetMapping("/getById")
    public R<StaffRespVo> getById(@RequestParam("id") Integer id) {
        return new R<>(staffService.findById(id));
    }

    /**
     * 新增记录
     * @param vo
     * @return R
     */
    @PostMapping("/add")
    public R add(@RequestBody StaffAddReqVo vo) {
        return new R<>(staffService.addOrUpdate(vo));
    }

    /**
     * 修改记录
     * @param vo
     * @return R
     */
    @PutMapping("/updateById")
    public R updateById(@RequestBody StaffAddReqVo vo) {
        return new R<>(staffService.addOrUpdate(vo));
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
}