package com.demo.xyz.common.core.util.wrapper;

import lombok.Data;

/**
 * 解压图片文件信息
 */
@Data
public class UnZipImageFileInfo extends UnZipFileInfo {
    /**
     * 宽
     */
    private int width;
    /**
     * 高
     */
    private int height;

    private String name;

    //文件KEY
    private String key;
}
