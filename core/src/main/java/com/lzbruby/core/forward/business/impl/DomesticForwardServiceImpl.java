package com.lzbruby.core.forward.business.impl;

import com.lzbruby.core.forward.impl.ForwardContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 功能描述：国内分发实现
 *
 * @author: Zhenbin.Li
 * email： lizhenbin08@sina.cn
 * company：org.lzbruby
 * Date: 15/11/16 Time: 10:58
 */
@Service("domesticForwardService")
public class DomesticForwardServiceImpl extends DefaultForwardServiceImpl {

    /**
     * sl4j
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DomesticForwardServiceImpl.class);

    @Override
    public void update(ForwardContext context) {
        super.update(context);
    }
}
