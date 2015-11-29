package com.lzb.autocode.mybatis.generat.generator.impl;

import com.lzb.autocode.mybatis.generat.generator.context.GeneratorContext;
import com.lzb.autocode.mybatis.generat.generator.context.PackageConfigType;
import org.apache.velocity.VelocityContext;

/**
 * 功能描述：Manager层代码生成
 *
 * @author: Zhenbin.Li
 * email： lizhenbin@oneplus.cn
 * company：一加科技
 * Date: 15/6/13 Time：00:51
 */
public class ManagerGenerator extends BaseGenerator {

    @Override
    public void initVelocityContext(VelocityContext velocityContext, GeneratorContext generatorContext) {
        super.initVelocityContext(velocityContext, generatorContext);
    }

    @Override
    protected PackageConfigType getPackageConfigType() {
        return PackageConfigType.manager;
    }

    @Override
    protected String getDescription() {
        return " Manager";
    }
}
