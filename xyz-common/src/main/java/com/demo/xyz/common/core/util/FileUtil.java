package com.demo.xyz.common.core.util;

import cn.hutool.core.util.StrUtil;
import com.demo.xyz.common.core.constant.CommonConfig;
import com.demo.xyz.common.core.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
public class FileUtil {


    public static String getCephKey(String path) {

        String unixPath = FilenameUtils.separatorsToUnix(path);
        if (StringUtils.isBlank(unixPath)) {
            return path;
        }
        if (unixPath.startsWith("/")) {
            unixPath = unixPath.substring(1);
        }

        return unixPath;
    }

    public static String saveImage(byte[] inputBytes, String imageType, String moduleName) {

        if (inputBytes == null) {
            log.error("inputBytes is null.");
            return null;
        }

        moduleName = StringUtils.isEmpty(moduleName) ? "" : moduleName + File.separator;
        InputStream input = new ByteArrayInputStream(inputBytes);
        String relativePath = "/image/upload/" + moduleName + UUIDUtil.generateUUID() + imageType;
        String output = CommonConfig.CONFIG_CRSSDATA_HOME + relativePath;
        FileUtil.saveFile(input, output);
        return relativePath;
    }

    public static String saveImage(byte[] inputBytes, String imageType, String moduleName, boolean absolutePath) {
        String s = saveImage(inputBytes, imageType, moduleName);
        return getAbsolutePath(s);
    }

    public static String copyImage(String relativePath, String imageType, String moduleName) {
        File file = new File(getAbsolutePath(relativePath));
        BufferedInputStream input = cn.hutool.core.io.FileUtil.getInputStream(getAbsolutePath(relativePath));
        moduleName = StringUtils.isEmpty(moduleName) ? "" : moduleName + File.separator;
        String newRelativePath = "/image/upload/" + moduleName + UUIDUtil.generateUUID() + imageType;
        String output = CommonConfig.CONFIG_CRSSDATA_HOME + newRelativePath;
        FileUtil.saveFile(input, output);
        return newRelativePath;
    }

    /*
     * private String getTenantBaseDir(Integer tenantId) { StringBuilder sb = new
     * StringBuilder(); sb.append("/image").append("/face"); String paddingOrgid =
     * StringUtils.leftPad(tenantId.toString(), 5, "0");
     * sb.append("/").append(paddingOrgid);
     *
     * return sb.toString(); }
     */

    public static void saveFile(InputStream input, String output) {

        File outputFile = new File(output);
        if (!outputFile.getParentFile().exists()) {

            outputFile.getParentFile().mkdirs();
        }

        try (OutputStream os = new FileOutputStream(output);) {
            IOUtils.copy(input, os);
        } catch (IOException e) {
            throw new ServiceException("保存文件异常:" + e.getMessage());
        }

    }

    public static String saveVod(byte[] inputBytes, String fileName) {
        if (inputBytes == null) {
            log.error("inputBytes is null.");
            return null;
        }
        String relativeDir = "/vod/upload/" + fileName;
        InputStream input = new ByteArrayInputStream(inputBytes);
        String output = CommonConfig.CONFIG_CRSSDATA_HOME + relativeDir;
        FileUtil.saveFile(input, output);
        return relativeDir;

    }

    /**
     * 保存货柜监控文件
     *
     * @param inputBytes
     * @param fileName
     * @return
     */
    public static String saveVendingMonitorFile(byte[] inputBytes, String fileName) {
        if (inputBytes == null) {
            log.error("inputBytes is null.");
            return null;
        }
        String relativeDir = CommonConfig.CONFIG_VENDING_MONITOR_FILE_PATH + fileName;
        InputStream input = new ByteArrayInputStream(inputBytes);
        String output = CommonConfig.CONFIG_CRSSDATA_HOME + relativeDir;
        FileUtil.saveFile(input, output);
        return relativeDir;
    }

    public static String getAbsolutePath(String relativePath) {
        return CommonConfig.CONFIG_CRSSDATA_HOME + relativePath;
    }

    public static boolean deleteFile(String absolutePath) {
        boolean flag = true;
        try {
            FileUtils.forceDelete(new File(absolutePath));
        } catch (IOException e) {
            flag = false;
        }
        return flag;
    }

    public static boolean deleteFileWithRelativePath(String relativePath) {
        String absolutePath = getAbsolutePath(relativePath);
        return deleteFile(absolutePath);
    }

    /**
     * Description: 将保持在数据库的相对路径图片地址转换为绝对路径地址<br>
     *
     * @param <T>
     * @param list
     * @param pathField
     * @author alex.xu
     * @see
     */
    public static <T> void convertImageToAbsPath(Collection<T> list, String pathField) {
        List<T> col = new ArrayList<T>();
        if (list != null && !list.isEmpty()) {
            list.forEach(obj -> {
                col.add(convertImageToAbsPath(obj, pathField));
            });
        }

    }

    public static <T> T convertImageToAbsPath(T obj, String pathField) {
        String path;
        try {
            path = (String) PropertyUtils.getProperty(obj, pathField);
            if (path != null) {
                PropertyUtils.setProperty(obj, pathField, getAbsolutePath(path));
            }
        } catch (IllegalAccessException e) {
            log.error("convertImageToAbsPath() got Exception:{}", e.getMessage(), e);

        } catch (InvocationTargetException e) {
            log.error("convertImageToAbsPath() got Exception:{}", e.getMessage(), e);

        } catch (NoSuchMethodException e) {
            log.error("convertImageToAbsPath() got Exception:{}", e.getMessage(), e);
        }
        return obj;
    }

    public static boolean checkIsImageFile(String fileName) {

        int i = fileName.lastIndexOf('.');
        String fileType = fileName.substring(i + 1);
        if (fileType.equals("JPG") || fileType.equals("jpg") || fileType.equals("png") || fileType.equals("gif")
                || fileType.equals("tif") || fileType.equals("bmp") || fileType.equals("jpeg")) {
            return true;
        }
        return false;

    }

    public static String getFileName(String filePath) {

        String fileName = "";

        if (!StringUtils.isEmpty(filePath)) {
            int i = filePath.lastIndexOf(File.separator);
            fileName = filePath.substring(i + 1);
        }
        return fileName;

    }

    /**
     * 根据文件夹路径获取文件夹下所有文件
     *
     * @param folderPath
     * @return
     */
    public static File[] getFilesWithFolder(String folderPath) {
        File dir = new File(folderPath);
        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null && files.length > 0) {
                return files;
            }
        }
        return null;
    }

    /**
     * 获取CEPH路径
     *
     * @param key
     * @return
     */
    public static String getCephPath(String key) {
        return CommonConfig.CONFIG_CEPHDATA_PATH + StrUtil.removePrefix(key, "/");
    }

    public static <T> T convertImageToCephPath(T obj, String... pathField) {
        String path;
        try {
            for(String field:pathField){
                path = (String) PropertyUtils.getProperty(obj, field);
                if (path != null) {
                    PropertyUtils.setProperty(obj, field, getCephPath(path));
                }
            }
        } catch (IllegalAccessException e) {
            log.error("convertImageTooCephPath() get Exception:{}", e.getMessage(), e);

        } catch (InvocationTargetException e) {
            log.error("convertImageToCephPath() get Exception:{}", e.getMessage(), e);

        } catch (NoSuchMethodException e) {
            log.error("convertImageToCephPath() get Exception:{}", e.getMessage(), e);
        }
        return obj;
    }

    public static <T> void convertImageToCephPath(Collection<T> list, String... pathField) {
        List<T> col = new ArrayList<T>();
        if (list != null && !list.isEmpty()) {
            list.forEach(obj -> {
                col.add(convertImageToCephPath(obj, pathField));
            });
        }
    }
}
