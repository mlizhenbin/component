package com.lzbruby.core.forward.business.impl;

import com.lzbruby.core.forward.business.DefaultOperateService;
import com.lzbruby.core.forward.impl.OperateContext;
import com.lzbruby.core.forward.impl.OperateType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能描述：default impl
 *
 * @author: Zhenbin.Li
 * email： lizhenbin08@sina.cn
 * company：org.lzbruby
 * Date: 15/11/14 Time: 12:16
 */
@Service("defaultOperateService")
public class DefaultOperateServiceImpl implements DefaultOperateService {

    /**
     * sl4j
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultOperateServiceImpl.class);


    public void create(OperateContext context) {
    }

    public void update(OperateContext context) {
    }

    public List list(OperateContext context) {
        return null;
    }

    public Object findByKey(Object key) {
        return null;
    }

    public void delete(Object key) {
    }

    public OperateType getOperateType() {
        return OperateType.DEFAULT_OPERATE;
    }
}
