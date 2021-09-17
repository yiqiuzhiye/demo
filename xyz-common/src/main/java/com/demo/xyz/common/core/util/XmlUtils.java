package com.demo.xyz.common.core.util;

import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.URL;

/**
 * XML序列化
 */
@Slf4j
public class XmlUtils {

    /**
     * 反序列化
     *
     * @param clazz
     * @param resourceFilePath resources目录下文件路径
     * @param <T>
     * @return
     */
    public static <T> T deSerialize(Class<T> clazz, String resourceFilePath) {
        URL url = clazz.getClassLoader().getResource(resourceFilePath);
        return deSerialize(clazz, url);
    }

    /**
     * 反序列化
     *
     * @param clazz
     * @param url   指定路径
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T deSerialize(Class<T> clazz, URL url) {
        T t = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            t = (T) unmarshaller.unmarshal(url);
        } catch (JAXBException e) {
            log.error("deSerialize filePath={} err={}", url.toString(), e.getCause());
        }
        return t;
    }
}
