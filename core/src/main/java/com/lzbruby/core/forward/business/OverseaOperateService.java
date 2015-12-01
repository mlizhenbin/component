package com.lzbruby.core.forward.business;

import com.lzbruby.core.forward.Operator;
import com.lzbruby.core.forward.impl.OperateContext;

import java.util.List;

/**
 * 功能描述：海外操作接口
 *
 * @author: Zhenbin.Li
 * email： lizhenbin@oneplus.cn
 * company：一加科技
 * Date: 15/11/14 Time: 12:19
 */
public interface OverseaOperateService<E extends OperateContext, R> extends Operator {

    /**
     * 通过编码获取所有的上下文
     *
     * @param codes
     * @return
     */
    public List<R> listOverseaCountiesByCodes(String[] codes);
}
