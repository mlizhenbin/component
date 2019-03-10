package com.lzbruby.core.direct.biz.dao.model;

import com.lzbruby.lang.NoNullStyle;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 功能描述: ConfigDict Model
 *
 * @author: lizhenbin
 * email: lizhenbin08@sina.cn
 * company: org.lzbruby
 * Date: 2015/11/30 Time: 11:14:32
 */
public class ConfigDict implements Serializable {
    private static final long serialVersionUID = -6434235900793736592L;

    /**
     * 可见.
     */
    public static final Integer VALID_FALG_YES = 1;

    /**
     * 不可见.
     */
    public static final Integer VALID_FALG_NO = 0;

    /**
     * 主键ID，自增
     */
    private Long id;

    /**
     * 配置类型
     */
    private String configType;

    /**
     * 配置KEY
     */
    private String key;

    /**
     * 配置value
     */
    private String value;

    /**
     * 配置描述
     */
    private String configDesc;

    /**
     * 审核状态
     */
    private String validFlag;

    /**
     * 排序值
     */
    private Long orderId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getConfigDesc() {
        return configDesc;
    }

    public void setConfigDesc(String configDesc) {
        this.configDesc = configDesc;
    }

    public String getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, new NoNullStyle());
    }
}
