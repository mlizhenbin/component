package com.lzbruby.core.forward.impl;

import com.google.common.collect.Maps;
import com.lzbruby.core.forward.Operator;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * 功能描述：操作接口管理器
 *
 * @author: Zhenbin.Li
 * email： lizhenbin08@sina.cn
 * company：org.lzbruby
 * Date: 15/11/14 Time: 11:57
 */
public class OperatorBeanManager implements ApplicationContextAware {

    /**
     * sl4j
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(OperatorBeanManager.class);

    /**
     * 配置操作接口map
     */
    private static Map<OperateType, Operator<? extends OperateContext, ?>> maps = Maps.newHashMap();

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Operator> beanMaps = applicationContext.getBeansOfType(Operator.class);
        for (String key : beanMaps.keySet()) {
            Operator<? extends OperateContext, ?> operateManager =
                    (Operator<? extends OperateContext, ?>) applicationContext.getBean(key);
            maps.put(operateManager.getOperateType(), operateManager);
            LOGGER.info("获取SalesOrderOperateManager的Bean：" + operateManager.getOperateType().getDesc());
        }
    }

    /**
     * 通过配置操作类型获取操作接口
     *
     * @param operateType
     * @return
     */
    public static Operator<? extends OperateContext, ?> getOperatorByType(OperateType operateType) {
        if (operateType == null) {
            return null;
        }

        return maps.get(operateType);
    }

    public static Operator<? extends OperateContext, ?> getOperatorByType(String operateType) {
        if (StringUtils.isBlank(operateType)) {
            return null;
        }
        OperateType type = OperateType.getByType(operateType);
        return getOperatorByType(type);
    }
}
