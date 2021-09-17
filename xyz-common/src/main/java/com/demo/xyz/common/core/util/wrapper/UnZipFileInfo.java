package com.demo.xyz.common.core.util.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 解压文件信息
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UnZipFileInfo {
    /**
     * 注释
     */
    private String comment;
    /**
     * 路径
     */
    private String path;
    /**
     * 文件大小
     */
    private long size;
}
