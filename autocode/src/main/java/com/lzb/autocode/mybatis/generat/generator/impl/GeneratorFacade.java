package com.lzb.autocode.mybatis.generat.generator.impl;

import com.google.common.collect.Maps;
import com.lzb.autocode.mybatis.generat.generator.Generator;
import com.lzb.autocode.mybatis.generat.generator.context.GeneratorContext;
import com.lzb.autocode.mybatis.generat.generator.context.PackageConfigType;
import org.apache.commons.collections.MapUtils;

import java.util.Iterator;
import java.util.Map;

/**
 * 功能描述：分发生成代码适配器
 *
 * @author: Zhenbin.Li
 * email： lizhenbin@oneplus.cn
 * company：一加科技
 * Date: 15/6/14 Time：16:36
 */
public class GeneratorFacade implements Generator {

    private Map<PackageConfigType, Generator> generatorMap = Maps.newHashMap();

    public void defaultGenerator(GeneratorContext context, PackageConfigType configType) {
        getGenerator(configType).defaultGenerator(context, configType);
    }

    public void pluginGenerator(GeneratorContext context, PackageConfigType configType) {
        getGenerator(configType).pluginGenerator(context, configType);
    }

    public void setGeneratorMap(Map<String, Generator> map) {
        if (MapUtils.isNotEmpty(map)) {
            Iterator<Map.Entry<String, Generator>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Generator> entry = iterator.next();
                String key = entry.getKey();
                PackageConfigType configType = PackageConfigType.getByType(key);
                if (null == configType) {
                    throw new RuntimeException("根据key找不到生成代码的Generator，key = " + key);
                }
                this.generatorMap.put(configType, entry.getValue());
            }
        }
    }

    public Generator getGenerator(PackageConfigType configType) {
        return generatorMap.get(configType);
    }
}
