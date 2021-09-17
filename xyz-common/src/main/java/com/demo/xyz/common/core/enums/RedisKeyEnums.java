package com.demo.xyz.common.core.enums;

import lombok.Getter;

/**
 * @author james
 * @des Redis缓存 key 枚举类
 */
@Getter
public enum RedisKeyEnums {
    GINGRELITE_BIND("gingerLiteBind:bind:",60L);


    private String keyPrefix;
    private Long  epirationDate;

    RedisKeyEnums(String keyPrefix, Long epirationDate) {
        this.keyPrefix=keyPrefix;
        this.epirationDate=epirationDate;
    }

    public String getKey(String value){
        return this.keyPrefix+value;
    }
}
