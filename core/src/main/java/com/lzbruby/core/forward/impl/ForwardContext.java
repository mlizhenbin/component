package com.lzbruby.core.forward.impl;

import java.io.Serializable;

/**
 * 功能描述：分发模式上下文
 *
 * @author: Zhenbin.Li
 * email： lizhenbin@oneplus.cn
 * company：一加科技
 * Date: 15/11/16 Time: 10:22
 */
public class ForwardContext implements Serializable {
    private static final long serialVersionUID = -1085353318478860109L;

    /**
     * 类型
     */
    private ForwardType distributeType;

    public ForwardType getDistributeType() {
        return distributeType;
    }

    public void setDistributeType(ForwardType distributeType) {
        this.distributeType = distributeType;
    }
}
