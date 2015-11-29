package com.lzb.autocode.mybatis.generat.config;

/**
 * 功能描述：获取配置类工厂
 *
 * @author: Zhenbin.Li
 * email： lizhenbin@oneplus.cn
 * company：一加科技
 * Date: 15/6/12 Time：23:37
 */
public class GeneratorConfigurerFactory {

    /**
     * 自动化生成代码配置器
     */
    private static GeneratorConfigurer generatorConfigurer;

    /**
     * 获取配置
     *
     * @return
     */
    public synchronized static GeneratorConfigurer getGeneratorConfigurer() {
        if (null == generatorConfigurer) {
            generatorConfigurer = new DefaultGeneratorConfigurer();
        }
        return generatorConfigurer;
    }

}
