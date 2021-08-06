package com.demo.xyz.demo.util;

public class ThreadLocalUtils {
    private static final ThreadLocal<String> RESOURCES = new InheritableThreadLocal<String>();
    public static String getValue() {
        return RESOURCES.get();
    }

    public static void setValue(String  value) {
         RESOURCES.set(value);
    }


    public static  void remove() {
        RESOURCES.remove();
    }
}
