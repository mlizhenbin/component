package com.lzbruby.core.forward.impl;

import com.google.common.collect.Maps;
import com.lzbruby.core.forward.Forwarder;
import org.apache.commons.collections.MapUtils;

import java.util.Iterator;
import java.util.Map;

/**
 * 功能描述：业务接口转发
 *
 * @author: Zhenbin.Li
 * email： lizhenbin08@sina.cn
 * company：org.lzbruby
 * Date: 15/11/16 Time: 10:27
 */
public class ForwardFacade implements Forwarder {

    private Map<String, Forwarder> distributeMap = Maps.newHashMap();

    public Forwarder getDistribute(String distributeType) {
        return this.distributeMap.get(distributeType);
    }

    public void setDistributeMap(Map<String, Forwarder> map) {
        if (MapUtils.isNotEmpty(map)) {
            Iterator<Map.Entry<String, Forwarder>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Forwarder> entry = iterator.next();
                ForwardType type = ForwardType.getByType(entry.getKey());
                if (type == null) {
                    throw new RuntimeException("根据key找不到生成代码的Distribute，key = " + type);
                }
                String distributeType = type.getType();
                this.distributeMap.put(distributeType, entry.getValue());
            }
        }
    }

    public void create(ForwardContext context) {
        getDistribute(context.getDistributeType().getType()).create(context);
    }

    public void update(ForwardContext context) {
        getDistribute(context.getDistributeType().getType()).update(context);
    }

    public void delete(ForwardContext context) {
        getDistribute(context.getDistributeType().getType()).delete(context);
    }

    public void findById(ForwardContext context) {
        getDistribute(context.getDistributeType().getType()).findById(context);
    }

    public void list(ForwardContext context) {
        getDistribute(context.getDistributeType().getType()).list(context);
    }
}
