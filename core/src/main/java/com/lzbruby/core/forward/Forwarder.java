package com.lzbruby.core.forward;

import com.lzbruby.core.forward.impl.ForwardContext;

/**
 * 功能描述：门面模式分发接口
 *
 * @author: Zhenbin.Li
 * email： lizhenbin08@sina.cn
 * company：org.lzbruby
 * Date: 15/11/16 Time: 10:22
 */
public interface Forwarder {

    /**
     * create
     *
     * @param context
     */
    public void create(ForwardContext context);

    /**
     * update
     *
     * @param context
     */
    public void update(ForwardContext context);

    /**
     * delete
     *
     * @param context
     */
    public void delete(ForwardContext context);

    /**
     * find by key
     *
     * @param context
     */
    public void findById(ForwardContext context);

    /**
     * list
     *
     * @param context
     */
    public void list(ForwardContext context);

}
