package com.demo.xyz.auth.vo;

import java.io.Serializable;
import lombok.Data;

/**
 * 操作员查询参数
 *
 * @author Jiahong.Li
 * @since 2021-11-23
 */
@Data
public class StaffQueryVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;
}
