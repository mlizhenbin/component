package com.lzbruby.core.forward;


import com.lzbruby.core.forward.impl.ChooseOperateType;
import com.lzbruby.core.forward.impl.OperateContext;

import java.util.List;

/**
 * 功能描述：业务操作公共接口
 *
 * @author: Zhenbin.Li
 * email： lizhenbin@oneplus.cn
 * company：一加科技
 * Date: 15/11/14 Time: 11:36
 */
public interface Operator<E extends OperateContext, R> extends ChooseOperateType {

    /**
     * create
     *
     * @param context
     */
    public void create(E context);

    /**
     * update
     *
     * @param context
     */
    public void update(E context);

    /**
     * delete
     *
     * @param key
     * @param <T>
     */
    public <T> void delete(T key);

    /**
     * find by key
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> R findByKey(T key);

    /**
     * list
     *
     * @param context
     * @return
     */
    public List<R> list(E context);
}
