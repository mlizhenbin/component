package com.lzbruby.core.forward.impl;

import org.springframework.util.StringUtils;

/**
 * 功能描述：分发模式类型
 *
 * @author: Zhenbin.Li
 * email： lizhenbin@oneplus.cn
 * company：一加科技
 * Date: 15/11/16 Time: 10:24
 */
public enum ForwardType {

    DEFAULT_DISTRIBUTE("DEFAULT_DISTRIBUTE", "默认分发模式"),

    OVERSEA("OVERSEA", "海外"),

    DOMESTIC("DOMESTIC", "国内"),;

    private String type;

    private String desc;

    ForwardType(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static ForwardType getByType(String typeCode) {
        if (StringUtils.isEmpty(typeCode)) {
            return null;
        }

        for (ForwardType type : ForwardType.values()) {
            if (type.getType().equals(typeCode)) {
                return type;
            }
        }
        return null;
    }

}
