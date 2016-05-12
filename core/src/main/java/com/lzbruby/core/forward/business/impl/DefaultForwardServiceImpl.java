package com.lzbruby.core.forward.business.impl;

import com.lzbruby.core.forward.Forwarder;
import com.lzbruby.core.forward.impl.ForwardContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 功能描述：Distribute默认实现
 *
 * @author: Zhenbin.Li
 * email： lizhenbin08@sina.cn
 * company：org.lzbruby
 * Date: 15/11/16 Time: 10:36
 */
@Service("defaultForwardService")
public class DefaultForwardServiceImpl implements Forwarder {

    /**
     * sl4j
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultForwardServiceImpl.class);

    public void create(ForwardContext context) {

    }

    public void update(ForwardContext context) {

    }

    public void delete(ForwardContext context) {

    }

    public void findById(ForwardContext context) {

    }

    public void list(ForwardContext context) {

    }
}
