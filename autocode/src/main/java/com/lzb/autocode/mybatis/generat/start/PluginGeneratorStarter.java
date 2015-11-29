package com.lzb.autocode.mybatis.generat.start;


import com.lzb.autocode.mybatis.generat.generator.Generator;
import com.lzb.autocode.mybatis.generat.generator.context.GeneratorContext;
import com.lzb.autocode.mybatis.generat.generator.context.PackageConfigType;

/**
 * 功能描述：自动化生成代码插件执行实现
 *
 * @author: Zhenbin.Li
 * email： lizhenbin@oneplus.cn
 * company：一加科技
 * Date: 15/6/12 Time：23:41
 */
public class PluginGeneratorStarter extends DefaultGeneratorStarter {

    @Override
    protected void doGeneratorService(Generator generator, GeneratorContext generatorContext, PackageConfigType configType) {
        generator.pluginGenerator(generatorContext, configType);
    }
}
