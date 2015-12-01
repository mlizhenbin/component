package com.lzbruby.core.forward.business.impl;

import com.lzbruby.core.forward.impl.ForwardContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 功能描述：海外实现
 *
 * @author: Zhenbin.Li
 * email： lizhenbin@oneplus.cn
 * company：一加科技
 * Date: 15/11/16 Time: 10:55
 */
@Service("overseaForwardService")
public class OverseaForwardServiceImpl extends DefaultForwardServiceImpl {

    /**
     * sl4j
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(OverseaForwardServiceImpl.class);

    @Override
    public void create(ForwardContext context) {
        super.create(context);
    }
}
