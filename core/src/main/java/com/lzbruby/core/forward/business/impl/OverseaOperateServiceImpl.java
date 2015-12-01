package com.lzbruby.core.forward.business.impl;

import com.lzbruby.core.forward.business.OverseaOperateService;
import com.lzbruby.core.forward.impl.OperateContext;
import com.lzbruby.core.forward.impl.OperateType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能描述：
 *
 * @author: Zhenbin.Li
 * email： lizhenbin@oneplus.cn
 * company：一加科技
 * Date: 15/11/14 Time: 12:24
 */
@Service("overseaOperateService")
public class OverseaOperateServiceImpl implements OverseaOperateService {

    /**
     * sl4j
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(OverseaOperateServiceImpl.class);

    public List listOverseaCountiesByCodes(String[] codes) {
        return null;
    }

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
        return OperateType.OVERSEA;
    }
}
