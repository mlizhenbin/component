package com.lzbruby.core.forward.impl;

import org.apache.commons.lang.StringUtils;

/**
 * 功能描述：支持的业务类型
 *
 * @author: Zhenbin.Li
 * email： lizhenbin08@sina.cn
 * company：org.lzbruby
 * Date: 15/11/14 Time: 11:38
 */
public enum OperateType {

    DEFAULT_OPERATE("DEFAULT", "默认测试"),

    OVERSEA("OVERSEA", "海外");

    private String type;

    private String desc;

    OperateType(String type, String desc) {
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

    public static OperateType getByType(String typeCode) {
        if (StringUtils.isEmpty(typeCode)) {
            return null;
        }

        for (OperateType type : OperateType.values()) {
            if (type.getType().equals(typeCode)) {
                return type;
            }
        }
        return null;
    }
}
